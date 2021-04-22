package com.doordash.sample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.doordash.sample.R
import com.doordash.sample.api.retrofit.Network
import com.doordash.sample.model.storedetail.RestaurantDetailResponse
import com.doordash.sample.utils.ViewUtils
import com.doordash.sample.viewmodel.RestaurantViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_item_detail.*
import timber.log.Timber
import java.util.Locale

/**
 * A fragment representing a single restaurant item detail screen.
 */
class ItemDetailFragment : Fragment() {

  private var itemId: Int? = null
  private var restaurantViewModel: RestaurantViewModel? = null
  private var restaurantDetailObserver: Observer<RestaurantDetailResponse?>? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      if (it.containsKey(ARG_ITEM_ID)) {
        itemId = it.getInt(ARG_ITEM_ID)
      }
    }
    initRestaurantViewModel()
    fetchRestaurantDetails()
  }

  /**
   * Fetch restaurant details from Api
   */
  private fun fetchRestaurantDetails() {
    itemId?.let {
      restaurantViewModel?.fetchRestaurantDetail(it)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_item_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpLiveDataObserver()
  }

  /**
   * Initializing the Restaurant ViewModel
   */
  private fun initRestaurantViewModel() {
    context?.let {
      val viewModelFactory = RestaurantViewModel.RestaurantViewModelFactory(
          Network.getDoorDashApi(
              it
          ))
      restaurantViewModel = ViewModelProviders.of(this, viewModelFactory).get(
          RestaurantViewModel::class.java)
    }
  }

  /**
   * Setting up livedata observer which observes all the live data updates
   */
  private fun setUpLiveDataObserver() {
    restaurantDetailObserver = Observer {
      if (it == null) {
        ViewUtils.toggleProgressIndicator(main_progress)
        Timber.e("no restaurant feed results fetched")
      } else {
        ViewUtils.toggleProgressIndicator(main_progress)
        Timber.i("success api reponse: $it")
        setupUi(it)
      }
    }
    restaurantViewModel?.restaurantDetailLiveData?.observe(
        this,
        restaurantDetailObserver as Observer<RestaurantDetailResponse?>
    )
  }

  /**
   * Setup the UI items
   * @param it RestaurantDetailResponse
   */
  private fun setupUi(it: RestaurantDetailResponse) {
    getCollapsingToolbar()?.title = it.business?.name
    item_description.text = it.description
    restaurant_ratings.text = it.averageRating.toString()
    restaurant_review_count.text = getString(R.string.review_count,it.numberOfRatings.toString())
    restaurant_fee.text = getString(R.string.delivery_fee_label,it.deliveryFeeDetails?.originalFee?.displayString.toString())
    address.text = it?.address?.printableAddress
  }

  /**
   * Get Collapsing Toolbar view
   */
  private fun getCollapsingToolbar() = activity?.findViewById<CollapsingToolbarLayout>(
      R.id.toolbar_layout)

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ITEM_ID = "item_id"
  }
}