package com.restaurant.customhorizontalviewpager.module.data

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel


@Parcel
data class Restaurant(

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("desc")
    val desc: String = "",

    @field:SerializedName("cuisine")
    val cuisine: String = "",

    @field:SerializedName("features")
    val features: ArrayList<String>? = null,

    @field:SerializedName("location")
    val location: String = "",

    @field:SerializedName("avg_cost")
    val avgCost: String = "",

    @field:SerializedName("ratings")
    val ratings: Double = 0.0,

    @field:SerializedName("restaurant_food_images")
    val foodImages: ArrayList<String>? = null,

    @field:SerializedName("profile_image")
    val restaurantProfileImage: String = "",

    @field:SerializedName("restaurant_images")
    val restaurantMoreImages: ArrayList<String>? = null,

    @field:SerializedName("restaurant_event_images")
    val restaurantEventImages: ArrayList<String>? = null,

    @field:SerializedName("open_hours")
    val openHours: String = ""
)

