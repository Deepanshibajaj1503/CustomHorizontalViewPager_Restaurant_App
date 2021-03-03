package com.restaurant.customhorizontalviewpager.module.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.restaurant.customhorizontalviewpager.R
import com.restaurant.customhorizontalviewpager.module.adapters.RestaurantFoodAdapter
import com.restaurant.customhorizontalviewpager.module.commonUtils.Constants
import com.restaurant.customhorizontalviewpager.module.commonUtils.DialogBaseActivity
import com.restaurant.customhorizontalviewpager.module.commonUtils.RecyclerViewSnapHelper
import kotlinx.android.synthetic.main.activity_restaurant_food_images.*
import org.parceler.Parcels

class RestaurantFoodImagesActivity : DialogBaseActivity() {


    private var foodList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_food_images)

        performActivityEntryAnimation()
        openBundle()
        initUI()
        setOnClicks()
    }


    private fun openBundle() {
        intent.extras?.let {
            foodList = Parcels.unwrap(it.getParcelable<Parcelable>(Constants.BundleKeys.RESTAURANT_FOOD_IMAGES))
        }
    }

    private fun initUI() {
        foodList?.let { eventList ->
            food_images_big_layout_rv.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false
            )
            food_images_big_layout_rv.adapter = RestaurantFoodAdapter(eventList,
                Constants.IMAGE_LAYOUT.BIG) { item ->
                //do nothing
            }

            val snapHelper: SnapHelper = RecyclerViewSnapHelper()
            snapHelper.attachToRecyclerView(food_images_big_layout_rv)
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

    private fun setOnClicks() {
        back_btn.setOnClickListener {
            finish()
        }
    }
}