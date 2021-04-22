package com.doordash.sample.api.retrofit

import com.doordash.sample.model.storedetail.RestaurantDetailResponse
import com.doordash.sample.model.storefeed.RestaurantFeedResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit Api interface to fetch the restaurant and store feed
 */
interface RetrofitApi {
  @GET("v1/store_feed")
  fun getRestaurantFeed(@Query("lat") lat: Double, @Query("lng") lng: Double, @Query("offset") offset: Int,
      @Query("limit") limit: Int): Observable<Response<RestaurantFeedResponse>>

  @GET("v2/restaurant/{restaurant_id}")
  fun getRestaurantDetail(@Path("restaurant_id") id: Int): Observable<Response<RestaurantDetailResponse>>
}
