package com.restaurant.customhorizontalviewpager.module.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.restaurant.customhorizontalviewpager.module.commonUtils.KotlinEvent
import com.restaurant.customhorizontalviewpager.module.commonUtils.getJsonDataFromAsset
import com.restaurant.customhorizontalviewpager.module.data.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.disposables.CompositeDisposable

class RestaurantInfoViewModel : ViewModel() {

    private val mDisposable = CompositeDisposable()
    private val event: MutableLiveData<KotlinEvent> = MutableLiveData()
    val errorMsg = MutableLiveData<String>()
    private val restaurantResponse = MutableLiveData<List<Restaurant>>()

    override fun onCleared() {
        mDisposable.dispose()
        super.onCleared()
    }

    fun eventListener(): LiveData<KotlinEvent> {
        return event
    }

    fun getRestaurantData(): LiveData<List<Restaurant>> {
        return restaurantResponse
    }

    /* FETCH DATA FROM SERVER */
    fun fetchRestaurantDataFromServer() {
        /*  currently fetching data from json stored in
   *  assets folder */
    }

    private fun createRestaurantList(response: List<Restaurant>) {
        restaurantResponse.value = response
        event.value = KotlinEvent.Companion.CompletedEvent
    }

    /* FETCH DATA FROM LOCAL JSON */
    fun fetchTeamDataFromLocalStorage(context: Context) {

        val teamJson = getJsonDataFromAsset(context, "restaurants.json")

        val gson = Gson()
        val listType = object : TypeToken<List<Restaurant>>() {}.type

        val restaurantList: List<Restaurant> = gson.fromJson(teamJson, listType)

        if (restaurantList.isNotEmpty()) {
            createRestaurantList(restaurantList)
        }

    }
}