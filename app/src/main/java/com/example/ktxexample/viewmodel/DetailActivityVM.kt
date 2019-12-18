package com.example.ktxexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ktxexample.app_interface.DefaultDispatcherProvider
import com.example.ktxexample.app_interface.DispatcherProvider
import com.example.ktxexample.base.BaseVM
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.utils.Log

class DetailActivityVM(private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()) :
    BaseVM() {

    private lateinit var feed: BaseNewsFeed
    lateinit var baseNewsFeed: MutableLiveData<BaseNewsFeed>

    fun setFeed(feed: BaseNewsFeed) {
        baseNewsFeed = MutableLiveData(feed)
        Log.e("feed")
    }





}