package com.restaurant.customhorizontalviewpager.module.commonUtils


open class KotlinEvent {
    /**
     * Generic Loading KotlinEvent
     */
    companion object {

        object LoadingEvent : KotlinEvent()
        /**
         * Generic Success KotlinEvent
         */

        object CompletedEvent : KotlinEvent()

        object InternetError : KotlinEvent()

        object WentWrongEvent : KotlinEvent()

        /**
         * Generic Failed KotlinEvent
         */
        data class FailedEvent(val error: String) : KotlinEvent()

        val NO_INTERNET = -10
        val SHOW_PROGRESS = -11
        val HIDE_PROGRESS = -12
        val SOMETHING_WRONG = -13
        val API_FAILURE = -14
        val API_SUCCESS = -15

    }
}