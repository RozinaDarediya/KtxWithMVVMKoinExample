package com.example.ktxexample.utils

class AppConstants {

    companion object {
        const val pref_token = "prefToken"

        const val FEED_ALL = 0
        const val FEED_TRAVEL = 1
        const val FEED_HEALTH = 2
        const val FEED_WEATHER = 3
        const val FEED_ADVISORY = 4

        val TRIP_TYPE_UPCOMING = "Upcoming Trips"
        val TRIP_TYPE_PAST = "Past Trips"
        val TRIP_TYPE_CURRENT = "Current Trips"
        val TRIP_PAYMENT_STATUS_NOT_PAID = "BUY NOW at \$"  // paymentStatus = 0 , yellow
        val TRIP_PAYMENT_STATUS_PAID = "Covered"  //paymentStatus = 1 , green
        val TRIP_PAYMENT_STATUS_NOT_COVERED = "Not Covered"  //paymentStatus = 2 , red

    }
}