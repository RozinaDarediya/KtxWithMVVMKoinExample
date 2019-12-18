package com.example.ktxexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ktxexample.app_interface.DefaultDispatcherProvider
import com.example.ktxexample.app_interface.DispatcherProvider
import com.example.ktxexample.base.BaseVM
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.state.CommonState
import com.example.ktxexample.state.LoginScreenState
import com.example.ktxexample.utils.Log

class DetailActivityVM(private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()) :
    BaseVM() {

    lateinit var baseNewsFeed: MutableLiveData<BaseNewsFeed>
    var emailLiveData = MutableLiveData<String>("")

    private var state = MutableLiveData<CommonState>(CommonState.Initial)
    fun state(): LiveData<CommonState> = state

    fun setFeed(feed: BaseNewsFeed) {
        baseNewsFeed = MutableLiveData(feed)
        Log.e("feed")
        emailLiveData = MutableLiveData(feed.title)
    }

    fun onCloseClick() {
        state.postValue(CommonState.BackClick)
    }

    fun onLinkClick() {
        state.postValue(CommonState.LinkClick("link"))
    }


}