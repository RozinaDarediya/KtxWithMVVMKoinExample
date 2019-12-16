package com.example.ktxexample.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ktxexample.BuildConfig
import com.example.ktxexample.app_interface.DefaultDispatcherProvider
import com.example.ktxexample.app_interface.DispatcherProvider
import com.example.ktxexample.base.BaseVM
import com.example.ktxexample.local.AppDatabase
import com.example.ktxexample.model.request.DeviceInfo
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.model.response.feed_model.RSS.Feed
import com.example.ktxexample.remote.ApiRSSService
import com.example.ktxexample.remote.ApiService
import com.example.ktxexample.state.LoginScreenState
import com.example.ktxexample.utils.ApiConstants
import com.example.ktxexample.utils.AppConstants
import com.example.ktxexample.utils.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.inject
import java.util.HashMap
import java.util.regex.Pattern

class LoginActivityVM(private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()) :
    BaseVM() {

    private val emailPattern: Pattern by inject()
    private val apiService: ApiService by inject()
    private val apiRssService: ApiRSSService by inject()
    private val deviceInfo: DeviceInfo by inject()
    private val appDatabase: AppDatabase by inject()

    var emailLiveData = MutableLiveData<String>("")
    var passwordLiveData = MutableLiveData<String>("")
    private var state = MutableLiveData<LoginScreenState>(LoginScreenState.Initial)

    fun state(): LiveData<LoginScreenState> = state

    init {
        if (BuildConfig.BUILD_TYPE == "debug") {
            emailLiveData = MutableLiveData<String>("androidnurse@mailcatch.com")
            passwordLiveData = MutableLiveData<String>("123456")
        }
    }

    fun userPreference() {
        try {

        } catch (e: Exception) {
            Log.e(e.toString())
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch(dispatcher.default()) {
        if (email.isBlank()) {
            state.postValue(LoginScreenState.EmailValidationError("Email cannot be empty"))
            return@launch
        } else if (!emailPattern.matcher(email).matches()) {
            state.postValue(LoginScreenState.EmailValidationError("Invalid email address"))
            return@launch
        }

        if (password.isBlank()) {
            state.postValue(LoginScreenState.PssswordValidatorError("Password cannot be empty"))
            return@launch
        } else if (password.length !in 6..20) {
            state.postValue(LoginScreenState.PssswordValidatorError("Password must be 8 to 20 character long"))
            return@launch
        }

        state.postValue(LoginScreenState.Loading)
        val devicePlatform = deviceInfo.platformType
        val deviceUniqueId = deviceInfo.deviceUDID
        val deviceModel = deviceInfo.deviceModel
        val os = deviceInfo.os
        val deviceToken = deviceInfo.deviceToken

        val map = HashMap<String, String>()
        map.put(ApiConstants.email, email)
        map.put(ApiConstants.password, password)
        map.put(ApiConstants.device_platform, devicePlatform)
        map.put(ApiConstants.device_unique_id, deviceUniqueId)
        map.put(ApiConstants.device_token, deviceToken)
        map.put(ApiConstants.device_model, deviceModel)
        map.put(ApiConstants.os, os)

        kotlin.runCatching {
            apiService.userLogin(map)
        }.fold({
            state.postValue(LoginScreenState.LoginSuccess(it))
            appDatabase.userInfoDao().insertRecord(it.response.userInfo)
            appDatabase.deviceInfo().insertRecord(it.response.deviceInfo)
        }, {
            state.postValue(LoginScreenState.ApiFailure(it))
        })
    }

    fun combineApiCall() = viewModelScope.launch(dispatcher.default()) {
        var result1: Feed? = null
        var result2: Feed? = null
        kotlin.runCatching {
            val work1 = async { apiRssService.getDataDeferred("weather+India") }
            result1 = work1.await()
            val work2 = async { apiRssService.getDataDeferred("health+India") }
            result2 = work2.await()
        }.fold({
           val list =  computeResult(result1, result2)
            state.postValue(LoginScreenState.FeedResponse(list))
        }, {
            Log.e("data")
        })
    }

    private fun computeResult(result1: Feed?, result2: Feed?): ArrayList<Feed> {
        val list: ArrayList<Feed> = ArrayList()
        result1?.let { list.add(it) }
        if (result2 != null) {
            list.add(result2)
        }
        return list
    }

}