package com.example.ktxexample.utils

import android.annotation.SuppressLint
import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Rozina on 2019-07-09.
 */
class DateUtils {

    companion object {

        val GMT_FORMAT = "EEE, d MMM yyyy HH:mm:ss ZZZ"
        val SIMPLE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        val MSG_LIST_TIME_FORMAT = "MMM dd, yyyy"


        //Tue, 10 Sep 2019 17:52:28 GMT
        @SuppressLint("SimpleDateFormat")
        fun getRSSDate(date: String): Long {
            var timestamp: Long = 0
            try {
                val dateFormat = SimpleDateFormat(GMT_FORMAT)
                val formattedDate = dateFormat.parse(date)
                val calender = Calendar.getInstance()
                calender.time = formattedDate
                timestamp = calender.timeInMillis
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timestamp
        }

        @SuppressLint("SimpleDateFormat")
        fun getTripDisplay(date: String): String {
            var returnDate = ""
            val serverDate = date.replace("T", " ").replace("Z", "")
            val serverDF = SimpleDateFormat(SIMPLE_FORMAT)
            try {
                val formatedDate: Date = serverDF.parse(serverDate)
                val finalDF = SimpleDateFormat(MSG_LIST_TIME_FORMAT)
                returnDate = finalDF.format(formatedDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return returnDate
        }
    }
}