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
import com.example.ktxexample.state.HomeScreenState
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

class HomeActivityVM(private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()) :
    BaseVM() {

    private val apiRssService: ApiRSSService by inject()

    private var state = MutableLiveData<HomeScreenState>(HomeScreenState.Initial)

    fun state(): LiveData<HomeScreenState> = state

    fun combineApiCall() = viewModelScope.launch(dispatcher.default()) {
        var result1: Feed? = null
        var result2: Feed? = null
        kotlin.runCatching {
            val work1 = async { apiRssService.getDataDeferred("weather+India") }
            result1 = work1.await()
            val work2 = async { apiRssService.getDataDeferred("health+India") }
            result2 = work2.await()
        }.fold({
            val list = computeResult(result1, result2)
            state.postValue(HomeScreenState.FeedResponse(list))
        }, {
            state.postValue(HomeScreenState.ApiFailure(it))
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