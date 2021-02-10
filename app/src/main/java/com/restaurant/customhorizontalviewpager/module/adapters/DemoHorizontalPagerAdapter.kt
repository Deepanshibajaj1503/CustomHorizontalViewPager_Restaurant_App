package com.restaurant.customhorizontalviewpager.module.adapters



import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.restaurant.customhorizontalviewpager.R
import com.restaurant.customhorizontalviewpager.module.data.Restaurant
import kotlinx.android.synthetic.main.layout_horizontal_vp_item.view.*


class DemoHorizontalPagerAdapter(var context: Context, var restaurantList: List<Restaurant>,
    private val onItemClickListener: (teamData: Restaurant) -> Unit) : PagerAdapter() {

    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view == p1
    }

    override fun getCount(): Int {
        return restaurantList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_horizontal_vp_item, container, false)
        setupLayout(view, restaurantList[position])
        container.addView(view)
        return view
    }

    /* Set UI on pager item */
    private fun setupLayout(view: View, restaurant: Restaurant) {
        view.apply {

            name_tv.text = restaurant.name
            desc_tv.text = restaurant.desc
            rating_tv.text = restaurant.ratings.toString()
            location_tv.text = restaurant.location

            if (!TextUtils.isEmpty(restaurant.cuisine)) {
                cuisine_tv.text = """${restaurant.cuisine.capitalize()}"""
            }

            if (!TextUtils.isEmpty(restaurant.restaurantProfileImage)) {

                val options: RequestOptions = RequestOptions()
                    .placeholder(R.drawable.rest_placeholder_image)
                    .error(R.drawable.rest_placeholder_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                Glide.with(context).load(restaurant.restaurantProfileImage)
                    .apply(options)
                    .into(restaurant_profile_imageView)
            }


            view.setOnClickListener {
                onItemClickListener(restaurant)
            }

        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }


}