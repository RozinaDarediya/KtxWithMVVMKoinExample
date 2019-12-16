package com.example.ktxexample.utils

import android.content.Context
import com.example.ktxexample.R
import com.example.ktxexample.base.AppApplication
import com.example.ktxexample.model.base.APIError
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException

class ParseApiError {

    companion object {

        fun handleError(e: Throwable, context: Context): String {
            var errorMsg = ""
            try {
                if (e is HttpException) {
                    if (e.code() == 401) {
                        appLogout(context)
                        return ""
                    } else {
                        errorMsg = parseError(e)
                        if (errorMsg == "")
                            errorMsg = context.getString(R.string.str_server_error)
                    }
                } else if (e is ConnectException || e is UnknownHostException) {
                    errorMsg = context.getString(R.string.str_no_internet)
                } else {
                    errorMsg = e.localizedMessage!!
                    if (errorMsg == "")
                        errorMsg = context.getString(R.string.str_no_internet)
                }
            } catch (e: java.lang.Exception) {
                errorMsg = e.localizedMessage!!
            }
            return errorMsg
        }

        private fun parseError(e: HttpException): String {

            val errorMsg: String
            if (e is HttpException) {
                if (e.code() == HttpURLConnection.HTTP_BAD_REQUEST) {  //  for requestcode 400
                    Log.e("From parseError: " + e.message())
                    val data =
                        Gson().fromJson(e.response()!!.errorBody()!!.string(), APIError::class.java)
                    errorMsg = data.errorMsg
                } else if (e.code() == 404) {
                    Log.e("From parseError: " + e.message())
                    errorMsg = "Requested page is not found"
                } else {
                    Log.e("From parseError: " + e.message())
                    val data = Gson().fromJson(
                        e.response()!!.errorBody()!!.string(),
                        APIError::class.java
                    )
                    errorMsg = data.errorMsg
                }
            } else {
                errorMsg = e.message.toString()
                Log.e("From parseError: $e")
            }
            return errorMsg
        }

        fun appLogout(context: Context) {

        }

    }
}