package com.doordash.sample.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.doordash.sample.R
import com.doordash.sample.R.string
import com.doordash.sample.model.storefeed.StoresItem
import com.doordash.sample.ui.activity.ItemDetailActivity
import com.doordash.sample.ui.fragment.ItemDetailFragment
import com.doordash.sample.utils.DateTimeUtils.Companion.getClosingTime
import kotlinx.android.synthetic.main.restaurant_item_list.view.*
import java.util.*

/**
 * Restaurant feed list recylerview adapter to populates the restaurant item list.
 */
class RestaurantItemRecyclerViewAdapter(
    private val context: Context?,
    private val restaurantItemList: ArrayList<StoresItem?>,
) : RecyclerView.Adapter<RestaurantItemRecyclerViewAdapter.ViewHolder>() {

  private var onClickListener: View.OnClickListener ?= null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.restaurant_item_list, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = restaurantItemList[position]

    holder.restaurantName.text = item?.name
    holder.restaurantFoodType.text = item?.description

    val closingMinutes = getClosingTime(item)
    toggleClosingTime(closingMinutes, holder)

    with(holder.itemView) {
      tag = item
      setOnClickListener(onClickListener)
    }
    loadImage(item, holder)
  }

  /**
   * Toggle closing time
   * @param closingMinutes closing minutes
   * @param holder view holder
   */
  private fun toggleClosingTime(closingMinutes: Int,
      holder: ViewHolder) {
    if (closingMinutes <= 0) {
      holder.restaurantCloseTime.text = context?.getString(string.closed)
    } else {
      holder.restaurantCloseTime.text = context?.getString(string.closing_time, closingMinutes)
    }
  }

  //Load image with Glide (with caching)
  private fun loadImage(
      item: StoresItem?,
      holder: ViewHolder
  ) {
    when {
      item?.coverImgUrl?.isNotEmpty() == true -> {
        context?.let {
          Glide
            .with(it)
            .load(item?.coverImgUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL) //apply caching
            .override(100, 100)
            .into(holder.restaurantImage)
        };
      }
      }
  }

  override fun getItemCount() = restaurantItemList.size

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val restaurantImage: ImageView = itemView.restaurant_image
    val restaurantName: TextView = itemView.restaurant_name
    val restaurantFoodType: TextView = itemView.restaurant_food_type
    val restaurantCloseTime: TextView = itemView.restaurant_close_time

    init {
      onClickListener = View.OnClickListener { v ->
        val position = layoutPosition
        when {
            position !== RecyclerView.NO_POSITION -> {
              val item = restaurantItemList[position]
               val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                 //Don't recreate the activity on up navigation
                 flags =(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                 putExtra(ItemDetailFragment.ARG_ITEM_ID, item?.id)
                }
                v.context?.startActivity(intent)
            }
        }
      }
    }
  }
}