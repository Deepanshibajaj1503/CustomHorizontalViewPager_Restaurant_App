package com.restaurant.customhorizontalviewpager.module.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.restaurant.customhorizontalviewpager.R
import com.restaurant.customhorizontalviewpager.module.adapters.DemoHorizontalPagerAdapter
import com.restaurant.customhorizontalviewpager.module.commonUtils.Constants
import com.restaurant.customhorizontalviewpager.module.commonUtils.CustomProgressBar
import com.restaurant.customhorizontalviewpager.module.commonUtils.KotlinEvent
import com.restaurant.customhorizontalviewpager.module.data.Restaurant
import com.restaurant.customhorizontalviewpager.module.model.RestaurantInfoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_restaurant_main.*
import org.parceler.Parcels

class RestaurantMainActivity : AppCompatActivity() {

    private var restaurantInfoViewModel: RestaurantInfoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_main)

        init()
    }

    private fun init() {
        renderViewModel()
        loadDataFromLocalStorage()
    }

    /* Initialize view Model*/
    private fun renderViewModel() {
        restaurantInfoViewModel = ViewModelProvider(this).get(RestaurantInfoViewModel::class.java)
        restaurantInfoViewModel!!.errorMsg.observe(this, Observer { showErrorMessage(it) })
        restaurantInfoViewModel!!.eventListener().observe(this, Observer { event ->
            event?.let {
                when (event) {
                    KotlinEvent.Companion.LoadingEvent -> handleProgress(true)
                    KotlinEvent.Companion.CompletedEvent -> handleProgress(false)
                    KotlinEvent.Companion.InternetError -> internetNotAvailable()
                    is KotlinEvent.Companion.FailedEvent -> showErrorMessage(event.error)
                }
            }
        })

        /* Observe this method to get team data */
        restaurantInfoViewModel!!.getRestaurantData().observe(this, Observer {
            renderRestaurantList(it)
        })
    }

    /* load Data from server */
    private fun loadData() {
        restaurantInfoViewModel!!.fetchRestaurantDataFromServer()
    }


    /*  currently fetching data from json stored in
    *  assets folder */
    private fun loadDataFromLocalStorage() {
        restaurantInfoViewModel!!.fetchTeamDataFromLocalStorage(this)
    }

    /* Render data on UI */
    private fun renderRestaurantList(restaurantList: List<Restaurant>?) {
        restaurantList?.let {
            horizontalViewPager.adapter = DemoHorizontalPagerAdapter(this, it) { restaurantData ->
                onRestaurantInfoClicked(restaurantData)
            }
        }
    }

    private fun onRestaurantInfoClicked(restaurant: Restaurant) {
        restaurant.let {
            val intent = Intent(this, RestaurantInfoActivity::class.java)
            val args = Bundle()
            args.putParcelable(Constants.BundleKeys.RESTAURANT_INFO, Parcels.wrap<Restaurant>(it))
            intent.putExtras(args)
            startActivity(intent)
        }
    }

    /* No Internet available msg */
    private fun internetNotAvailable() {
        showErrorMessage(Constants.ERROR_MESSAGE.NO_INTERNET)
    }

    /* Progress Bar */
    private fun handleProgress(showProgress: Boolean) {
        CustomProgressBar.getProgressbar(this).apply {
            if (showProgress) show() else dismissProgress()
        }
    }

    /* show error msg */
    private fun showErrorMessage(errorMsg: String?) {
        errorMsg?.let {
            val snackBar = Snackbar.make(root_layout, it, Snackbar.LENGTH_LONG);
            snackBar.show()
        }
    }

}