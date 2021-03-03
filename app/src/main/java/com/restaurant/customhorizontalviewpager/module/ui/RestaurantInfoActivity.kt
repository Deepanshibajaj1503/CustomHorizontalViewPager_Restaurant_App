package com.restaurant.customhorizontalviewpager.module.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.restaurant.customhorizontalviewpager.R
import com.restaurant.customhorizontalviewpager.module.adapters.RestaurantEventsAdapter
import com.restaurant.customhorizontalviewpager.module.adapters.RestaurantFeaturesGridAdapter
import com.restaurant.customhorizontalviewpager.module.adapters.RestaurantFoodAdapter
import com.restaurant.customhorizontalviewpager.module.adapters.RestaurantImagesAdapter
import com.restaurant.customhorizontalviewpager.module.commonUtils.Constants
import com.restaurant.customhorizontalviewpager.module.commonUtils.RecyclerViewSnapHelper
import com.restaurant.customhorizontalviewpager.module.data.Restaurant
import kotlinx.android.synthetic.main.activity_restaurant_info.*
import org.parceler.Parcels


class RestaurantInfoActivity : AppCompatActivity() {

    private var restaurantInfo: Restaurant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_info)

        performActivityEntryAnimation()
        openBundle()
        initUI()
        onClicks()
    }

    private fun onClicks() {
        back_btn.setOnClickListener {
            finish()
        }
    }

    private fun openBundle() {
        intent.extras?.let {
            restaurantInfo =
                Parcels.unwrap(it.getParcelable<Parcelable>(Constants.BundleKeys.RESTAURANT_INFO))
        }
    }

    /* Set Restaurant Info */
    private fun initUI() {

        if (this.applicationContext != null) {
            restaurantInfo?.let {
                restaurant_name_tv.text = it.name
                desc_cuisine_tv.text = """${it.desc} - ${it.cuisine}"""
                location_tv.text = it.location

                // top images for restaurant
                it.restaurantMoreImages?.let { restaurantImages ->

                    restaurant_all_images_rv.layoutManager = LinearLayoutManager(
                        this, LinearLayoutManager.HORIZONTAL, false
                    )
                    restaurant_all_images_rv.adapter = RestaurantImagesAdapter(restaurantImages)
                    restaurant_all_images_rv.isNestedScrollingEnabled = false

                    val snapHelper: SnapHelper = RecyclerViewSnapHelper()
                    snapHelper.attachToRecyclerView(restaurant_all_images_rv)
                }

                // features of restaurant
                it.features?.let { features ->

                    features_rv.layoutManager =
                        GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
                    features_rv.adapter = RestaurantFeaturesGridAdapter(features)
                    features_rv.isNestedScrollingEnabled = false
                }

                // food images of restaurant
                it.foodImages?.let { foodList ->
                    food_images_rv.layoutManager = LinearLayoutManager(
                        this, LinearLayoutManager.HORIZONTAL, false
                    )
                    food_images_rv.adapter = RestaurantFoodAdapter(foodList,Constants.IMAGE_LAYOUT.SMALL) { item ->
                        onFoodImageClicked(foodList)
                    }
                    food_images_rv.isNestedScrollingEnabled = false
                }

                // event images of restaurant
                it.restaurantEventImages?.let { eventList ->
                    event_images_rv.layoutManager = LinearLayoutManager(
                        this, LinearLayoutManager.HORIZONTAL, false
                    )
                    event_images_rv.adapter = RestaurantEventsAdapter(eventList,Constants.IMAGE_LAYOUT.SMALL) { item ->
                        onEventImageClicked(eventList)
                    }
                    event_images_rv.isNestedScrollingEnabled = false
                }
            }
        }
    }


    /* Go to events screen */
    private fun onEventImageClicked(eventList: ArrayList<String>) {
        eventList.let {
            val intent = Intent(this, RestaurantEventImagesActivity::class.java)
            val args = Bundle()
            args.putParcelable(Constants.BundleKeys.RESTAURANT_EVENT_IMAGES, Parcels.wrap(it))
            intent.putExtras(args)
            startActivity(intent)
        }
    }

    /* Go to food screen */
    private fun onFoodImageClicked(foodList: ArrayList<String>) {
        foodList.let {
            val intent = Intent(this, RestaurantFoodImagesActivity::class.java)
            val args = Bundle()
            args.putParcelable(Constants.BundleKeys.RESTAURANT_FOOD_IMAGES, Parcels.wrap(it))
            intent.putExtras(args)
            startActivity(intent)
        }
    }

    private fun performActivityExitAnimation() {
        overridePendingTransition(R.anim.no_anim, R.anim.slide_down)
    }

    private fun performActivityEntryAnimation() {
        overridePendingTransition(R.anim.slide_up, R.anim.no_anim)
    }

    override fun finish() {
        super.finish()
        performActivityExitAnimation()
    }

    override fun onResume() {
        super.onResume()
    }


}