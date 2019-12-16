package com.example.ktxexample.utils

class Log {
    companion object {
        private var DEBUG = true
        private var TAG = "KTX-Example"

        fun e(msg: String) {
            if (DEBUG)
                android.util.Log.e(TAG, msg)
        }

    }
}