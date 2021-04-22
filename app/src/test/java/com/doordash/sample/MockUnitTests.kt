package com.doordash.sample

import com.doordash.MockFileReader
import com.doordash.sample.api.retrofit.RetrofitApi
import com.doordash.sample.ui.activity.MainActivity
import com.doordash.sample.viewmodel.RestaurantViewModel
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Using Mock WebServer to test api end-points
 */
@RunWith(JUnit4::class)
class MockWebUnitTests {

    private val server: MockWebServer = MockWebServer()

    lateinit var retrofitApi: RetrofitApi
    lateinit var restaurantViewModel: RestaurantViewModel

    companion object{
        const val MOCK_WEBSERVER_PORT = 8080
    }

    @Before
    fun init() {
        server.start(MOCK_WEBSERVER_PORT)

        retrofitApi = Retrofit.Builder()
            .baseUrl(server.url("http://127.0.0.1:8080"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(RetrofitApi::class.java)

        restaurantViewModel = RestaurantViewModel(retrofitApi)
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    @Test
    fun `Json restaurant feed api parse correctly`() {
        server.apply {
            enqueue(MockResponse().setBody(MockFileReader("restaurant_feed_json_mock_success.json").content))
        }
        retrofitApi.getRestaurantFeed( MainActivity.DEFAULT_LAT, MainActivity.DEFAULT_LNG,0,10).test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertNoErrors()
    }
    @Test
    fun `JsonPlaceholder restaurant detail api parse correctly`() {
        server.apply {
            enqueue(MockResponse().setBody(MockFileReader("restaurant_detail_json_mock_success.json").content))
        }
        retrofitApi.getRestaurantDetail(30).test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertNoErrors()
    }
}