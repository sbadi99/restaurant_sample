package com.doordash.sample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doordash.sample.api.retrofit.RetrofitApi
import com.doordash.sample.model.storedetail.RestaurantDetailResponse
import com.doordash.sample.model.storefeed.RestaurantFeedResponse
import com.doordash.sample.model.storefeed.StoresItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber

/**
 * The RestaurantViewModel includes LiveData objects (MVVM approach per Android architectural components)
 * Using RxJava as well in conjunction with LiveData
 */

class RestaurantViewModel(private val retrofitApi: RetrofitApi) : ViewModel() {
  private val compositeDisposable = CompositeDisposable()

  var restaurantFeedLiveData = MutableLiveData<List<StoresItem?>>()
  var restaurantDetailLiveData = MutableLiveData<RestaurantDetailResponse?>()

  internal fun fetchRestaurantFeed(lat: Double, lng: Double, offset:Int,limit:Int) {
    val disposable = retrofitApi.getRestaurantFeed( lat, lng,offset,limit)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<Response<RestaurantFeedResponse>>() {
          override fun onNext(response: Response<RestaurantFeedResponse>) {
            restaurantFeedLiveData.value = response.body()?.stores
          }

          override fun onComplete() {}

          override fun onError(e: Throwable) {
            restaurantFeedLiveData.value = null
            Timber.e("error$e")

          }
        })
    compositeDisposable.add(disposable)
  }

  internal fun fetchRestaurantDetail(restaurantId:Int) {
    val disposable = retrofitApi.getRestaurantDetail(restaurantId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<Response<RestaurantDetailResponse>>() {
          override fun onNext(response: Response<RestaurantDetailResponse>) {
            restaurantDetailLiveData.value = response.body()
          }

          override fun onComplete() {}

          override fun onError(e: Throwable) {
            restaurantDetailLiveData.value = null
            Timber.e("error$e")

          }
        })
    compositeDisposable.add(disposable)
  }

  //Called when the activity is destroyed
  public override fun onCleared() {
    Timber.d("RestaurantViewModel onCleared()")
    compositeDisposable.dispose()
    super.onCleared()
  }

  class RestaurantViewModelFactory(private val retrofitApi: RetrofitApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
        return RestaurantViewModel(retrofitApi) as T
      }
      throw IllegalArgumentException("Unknown ViewModel class")
    }
  }
}