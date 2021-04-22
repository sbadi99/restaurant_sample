package com.doordash.sample.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doordash.sample.R
import com.doordash.sample.api.retrofit.Network
import com.doordash.sample.model.storefeed.StoresItem
import com.doordash.sample.ui.adapter.RestaurantItemRecyclerViewAdapter
import com.doordash.sample.utils.ViewUtils.Companion.toggleProgressIndicator
import com.doordash.sample.viewmodel.RestaurantViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


/**
 * An activity representing the restaurant feed list.
 */
class MainActivity : AppCompatActivity() {

  private var linearLayoutManager: LinearLayoutManager? = null
  private var restaurantViewModel: RestaurantViewModel? = null
  private var restaurantFeedObserver: Observer<List<StoresItem?>>? = null
  private var restaurantFeedRecyclerView: RecyclerView? = null
  private var offset = 0
  private var limit = 10

  companion object {
    //Default DoorDash HQ location
    const val DEFAULT_LAT = 37.4222740
    const val DEFAULT_LNG = -122.139956
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    toolbar.title = title

    //Initializing the restaurant view model & live data observer
    initRestaurantViewModel()
    setUpLiveDataObserver()

    setupRecyclerView(restaurant_feed_recylerview)
    setRecyclerViewScrollListener()

    //load first page
    toggleProgressIndicator(main_progress, true)
    fetchRestaurantFeed()
  }

  private val lastVisibleItemPosition: Int
    get() = linearLayoutManager?.findLastVisibleItemPosition()!!


  /**
   * Load next items as user scrolls
   */
  private fun setRecyclerViewScrollListener() {
    restaurant_feed_recylerview?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        when (restaurant_feed_recylerview.layoutManager?.itemCount) {
          lastVisibleItemPosition + 1 -> {
            offset = offset.plus(limit)
            toggleProgressIndicator(main_progress, true)
            fetchRestaurantFeed()
          }
        }
      }
    })
  }

  /**
   * Fetch restaurant Feed from Api
   */
  private fun fetchRestaurantFeed() {
    restaurantViewModel?.fetchRestaurantFeed(
        DEFAULT_LAT,
        DEFAULT_LNG, offset, limit
    )
  }


  /**
   * Initializing the Restaurant ViewModel
   */
  private fun initRestaurantViewModel() {
    val viewModelFactory = RestaurantViewModel.RestaurantViewModelFactory(
        Network.getDoorDashApi(
            this@MainActivity
        )
    )
    restaurantViewModel = ViewModelProviders.of(this, viewModelFactory).get(
        RestaurantViewModel::class.java)
  }

  /**
   * Setting up LiveData Observer which observes all the live data updates
   */
  private fun setUpLiveDataObserver() {
    restaurantFeedObserver = Observer {
      if (it == null) {
        toggleProgressIndicator(main_progress)
        Timber.e("no restaurant feed results fetched")
      }
      it?.let { storesItemList ->
        when {
          storesItemList.isEmpty() -> {
            toggleProgressIndicator(main_progress)
            Timber.d("restaurant feed results are empty")
          }
          else -> {
            toggleProgressIndicator(main_progress)
            val storesFeedList = arrayListOf<StoresItem?>()
            storesFeedList.addAll(storesItemList)
            populateRestaurantFeedUi(storesFeedList)
            Timber.i("success api reponse: $storesFeedList")
          }
        }
      }
    }

    restaurantViewModel?.restaurantFeedLiveData?.observe(
        this,
        restaurantFeedObserver as Observer<List<StoresItem?>>
    )
  }


  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.addItemDecoration(
        DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
    )
    linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    restaurant_feed_recylerview?.layoutManager = linearLayoutManager
    recyclerView.itemAnimator = DefaultItemAnimator()
    restaurantFeedRecyclerView = recyclerView
  }

  /**
   * Populating the searchRecyclerView adapter with the search result data
   */
  private fun populateRestaurantFeedUi(
      restaurantItemList: ArrayList<StoresItem?>
  ) {
    restaurantFeedRecyclerView?.adapter = RestaurantItemRecyclerViewAdapter(
        this@MainActivity,
        restaurantItemList
    )
    restaurantFeedRecyclerView?.adapter?.notifyDataSetChanged()
  }
}