package com.restaurant.customhorizontalviewpager.module.commonUtils

interface Constants {

    interface ERROR_MESSAGE {
        companion object {
            const val SOMETHING_WENT_WRONG = "Something went wrong!"
            const val NO_INTERNET = "Unable to connect to the Internet"
            const val COULD_NOT_CONNECT_TO_SERVER = "Unable to connect to Server"
        }
    }

    interface BundleKeys {
        companion object {
            const val RESTAURANT_INFO = "RestaurantInfo"
            const val RESTAURANT_EVENT_IMAGES = "RestaurantEventImages"
            const val RESTAURANT_FOOD_IMAGES = "RestaurantFoodImages"
        }
    }

    interface IMAGE_LAYOUT {
        companion object {
            const val SMALL = "small"
            const val MEDIUM = "medium"
            const val BIG = "big"
        }
    }

}