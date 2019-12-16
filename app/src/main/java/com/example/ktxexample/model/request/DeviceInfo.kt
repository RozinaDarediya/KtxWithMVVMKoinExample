package com.example.ktxexample.model.request

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import java.io.Serializable

/**
 * Created by Rozina on 11/04/19.
 */
class DeviceInfo(context: Context) : Serializable {

    var platformType = "android"
    var deviceToken = "token"
    @SuppressLint("HardwareIds")
    var deviceUDID = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    var os = "Android"
    var deviceModel = Build.MODEL

    var appName = "KTX-Example"
    var deviceSystemVersion = Build.VERSION.RELEASE
    var deviceName = Build.MANUFACTURER
    var development = "sandbox"
}