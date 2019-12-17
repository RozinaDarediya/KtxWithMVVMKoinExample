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

        val APP_DATE_FORMAT = "EEE, MMM dd"
        val APP_TIME_FORMAT = "h:mm a"

        val GMT_FORMAT = "EEE, d MMM yyyy HH:mm:ss ZZZ"

        val SIMPLE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        val GROUP_USER_DATE = "MMM-dd-yyyy"

        val CHAT_OBJECT_TIME = "MMM dd,yyy | HH:mm"
        val MSG_LIST_TIME_FORMAT = "MMM dd, yyyy"
        val TRIP_PAYMENT_FORMAT = "dd, MMM yyyy"

        val CHAT_MSG_OBJECT_TIME = "MMM-dd-yyy hh:mm a"
        val CHAT_MSG_OBJECT_SHORT_TIME = "hh:mm a"

        val PROFILE_DATE_FORMAT = "MM/dd/yyyy"
        val ENCOUNTER_DATE_FORMAT = "dd/MM/yyyy"

        val ISRAEL_DATE_PATTERN = "mm/d/yyyy hh:mm:ss a"
        val ISRAEL_DATE_PATTERN1 = "mm/d/yyyy HH:mm:ss"
        //val ISRAEL_DATE_PATTERN2 = "MM/dd/yyyy, HH:mm:ss a"  //MM/dd/yyyy, HH:mm:ss a
        val ISRAEL_DATE_PATTERN2 = "MM/dd/yyyy, HH:mm a"  //MM/dd/yyyy, HH:mm:ss a

        val TRIP_DATE_FORMAT = "MM-dd-yyyy"

        /**
         * TODO returns today date in string format
         *
         * in format dd/mm/yyyy
         */
        fun todayDate(): String {
            val currentDate = Calendar.getInstance().time
            val dateInString = DateFormat.format(ENCOUNTER_DATE_FORMAT, currentDate).toString()
            return dateInString
        }

        fun parseAndGetDateData(time: Long): String {
            val cal = Calendar.getInstance(Locale.getDefault())
            cal.timeInMillis = time
            return DateFormat.format(PROFILE_DATE_FORMAT, cal).toString()
        }

        fun destinationDate(time: Long): String {
            val cal = Calendar.getInstance(Locale.getDefault())
            cal.timeInMillis = time
            return DateFormat.format("EEEE, dd MMMM yyyy", cal).toString()
        }

        fun getYear(tripDate: String): Int {
            val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy")
            val date: Date = dateFormat.parse(tripDate)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.YEAR)
        }

        fun getMonth(tripDate: String): Int {
            val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy")
            val date: Date = dateFormat.parse(tripDate)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.MONTH)
        }

        fun getDay(tripDate: String): Int {
            val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy")
            val date: Date = dateFormat.parse(tripDate)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.DAY_OF_MONTH)
        }

        fun parseAndGetDateDataFormat(time: Long, format: String): String {
            val cal = Calendar.getInstance(Locale.getDefault())
            cal.timeInMillis = time
            return DateFormat.format(format, cal).toString()
        }

        @SuppressLint("SimpleDateFormat")
        fun getIsraelTime(time: String): Date? {
            var sdfDate: Date? = null
            try {
                val formatedDate = time.replace(",", "")
                val sdf = SimpleDateFormat(ISRAEL_DATE_PATTERN)
                sdf.timeZone = TimeZone.getTimeZone("GMT+03:00")
                sdfDate = sdf.parse(formatedDate)

                val sdf1 = SimpleDateFormat(ISRAEL_DATE_PATTERN1)
                sdf1.timeZone = TimeZone.getTimeZone("GMT+03:00")
                val sdfDate1 = sdf1.parse(formatedDate)
                Log.e(sdfDate1.toString())

                val sdf2 = SimpleDateFormat(ISRAEL_DATE_PATTERN2)
                sdf2.timeZone = TimeZone.getTimeZone("GMT+03:00")
                val sdfDate2 = sdf2.parse(time)
                //sdfDate = sdfDate2
                Log.e(sdfDate2.toString())

            } catch (e: Exception) {
                Log.e(e.toString())
            }
            return sdfDate
        }

        @SuppressLint("SimpleDateFormat")
        fun getChatObjectTime(date: String): String {
            var retrivalDate = ""
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val date1: Date = dateFormat.parse(date)
                val finalDF = SimpleDateFormat(CHAT_OBJECT_TIME)
                retrivalDate = finalDF.format(date1)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return retrivalDate
        }

        @SuppressLint("SimpleDateFormat")
        fun getInboxObjectTime(date: String): String {
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

        @SuppressLint("SimpleDateFormat")
        fun getTimestamp(date: String?): Long {
            var formatter: Long = 0
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val date1 =
                    dateFormat.parse(date)//You will get date object relative to server/client timezone wherever it is parsed
                formatter = date1.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return formatter

        }

        @SuppressLint("SimpleDateFormat")
        fun getDate(stringDate: String): Date {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val date: Date = dateFormat.parse(stringDate)
            return date
        }

        @SuppressLint("SimpleDateFormat")
        fun getMsgDate(date: Date): String? {
            var returnDate = ""
            if (isToday(date)) {
                val serverDF = SimpleDateFormat(CHAT_MSG_OBJECT_SHORT_TIME)
                returnDate = serverDF.format(date)
            } else {
                val serverDF = SimpleDateFormat(CHAT_MSG_OBJECT_TIME)
                returnDate = serverDF.format(date)
            }
            return returnDate
        }

        fun getReminderDate(date: Date): String? {
            var returnDate: String = ""
            try {
                val serverDF = SimpleDateFormat(CHAT_OBJECT_TIME)
                returnDate = serverDF.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return returnDate
        }

        fun isToday(d: Date): Boolean {
            return android.text.format.DateUtils.isToday(d.time)
        }

        fun isDateIsYesterday(d: Date): Boolean {
            return isToday(d)
        }

        @SuppressLint("SimpleDateFormat")
        fun getGroupUserDOB(date: String): String? {
            var returnDate: String = ""
            try {
                val dateFormat = SimpleDateFormat("MM/dd/yyyy");
                val d = dateFormat.parse(date)
                val serverDF = SimpleDateFormat(GROUP_USER_DATE)
                returnDate = serverDF.format(d)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return returnDate
        }

        //Mon, 11 Mar 2019
        @SuppressLint("SimpleDateFormat")
        fun getAdvisoryDate(date: String): Long {
            var timestamp: Long = 0
            try {
                val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
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
        fun getAdvisoryUpdatedDate(stringDate: String): Long {
            var timestamp: Long = 0
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val date: Date = dateFormat.parse(stringDate)
                val calender = Calendar.getInstance()
                calender.time = date
                timestamp = calender.timeInMillis
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timestamp
        }

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
        fun isEndDateSmall(startDate: String, endDate: String): Boolean {
            val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy")
            val date1 = dateFormat.parse(startDate)
            val date2 = dateFormat.parse(endDate)
            return date1 > date2
        }

        fun getFormatedTripDate(tripDate: String): String {
            var returnDate: String = ""
            val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy")
            val date = dateFormat.parse(tripDate)
            val serverDF = SimpleDateFormat(TRIP_DATE_FORMAT)
            returnDate = serverDF.format(date)
            return returnDate
        }


        @SuppressLint("SimpleDateFormat")
        fun getTripDisplay(date: String): String {
            var returnDate = ""

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val date1: Date = dateFormat.parse(date)

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

        @SuppressLint("SimpleDateFormat")
        fun getTripPaymentDate(date: String): String {
            var returnDate = ""

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val date1: Date = dateFormat.parse(date)

            val serverDate = date.replace("T", " ").replace("Z", "")
            val serverDF = SimpleDateFormat(SIMPLE_FORMAT)
            try {
                val formatedDate: Date = serverDF.parse(serverDate)
                val finalDF = SimpleDateFormat(TRIP_PAYMENT_FORMAT)
                returnDate = finalDF.format(formatedDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return returnDate
        }

        @SuppressLint("SimpleDateFormat")
        fun getType(startDate: String, endDate: String): String {
            var type = ""
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val startDt: Date = dateFormat.parse(startDate)
                val endDt: Date = dateFormat.parse(endDate)

                val today: Date = Date()
                if (startDt > today) {
                    type = AppConstants.TRIP_TYPE_UPCOMING
                } else if (endDt < today) {
                    type = AppConstants.TRIP_TYPE_PAST
                } else {
                    type = AppConstants.TRIP_TYPE_CURRENT
                }
            } catch (e: java.lang.Exception) {
                Log.e(e.toString())
            }
            return type
        }

        fun getTypeInt(startDate: String, endDate: String): Int {
            var type = 0
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val startDt: Date = dateFormat.parse(startDate)
                val endDt: Date = dateFormat.parse(endDate)
                val today: Date = Date()
                if (startDt > today) {
                    type = 2
                } else if (endDt < today) {
                    type = 3
                } else {
                    type = 1
                }
            } catch (e: java.lang.Exception) {
                Log.e(e.toString())
            }
            return type
        }

        @SuppressLint("SimpleDateFormat")
        fun getWeatherHour(timeStamp: Long): String? {
            val c = Calendar.getInstance()
            c.timeInMillis = timeStamp * 1000
            val d = c.time
            val sdf = SimpleDateFormat("h a")
            val hour = sdf.format(d)
            return hour.replace(" ", "")
        }

        @SuppressLint("SimpleDateFormat")
        fun getWeatherDay(timeStamp: Long): String? {
            val c = Calendar.getInstance()
            c.timeInMillis = timeStamp * 1000
            val d = c.time
            val sdf = SimpleDateFormat("EEEE")
            val day = sdf.format(d)
            return day
        }

        fun getSendMsgDate(): String {
            val cal = Calendar.getInstance(Locale.getDefault())
            return DateFormat.format(PROFILE_DATE_FORMAT, cal).toString()
        }

    }
}