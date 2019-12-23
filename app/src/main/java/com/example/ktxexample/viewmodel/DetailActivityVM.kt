package com.example.ktxexample.viewmodel

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ktxexample.app_interface.DefaultDispatcherProvider
import com.example.ktxexample.app_interface.DispatcherProvider
import com.example.ktxexample.base.BaseVM
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.state.CommonState
import com.example.ktxexample.state.LoginScreenState
import com.example.ktxexample.utils.DateUtils
import com.example.ktxexample.utils.Log
import kotlinx.coroutines.launch

class DetailActivityVM(private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()) :
    BaseVM() {

    lateinit var baseNewsFeed: MutableLiveData<BaseNewsFeed>
    var titleLiveData = MutableLiveData<String>("")
    var sourceLiveData = MutableLiveData<String>("")
    var pubDateLiveData = MutableLiveData<String>("")

    private var state = MutableLiveData<CommonState>(CommonState.Initial)
    fun state(): LiveData<CommonState> = state

    fun setFeed(feed: BaseNewsFeed) {
        baseNewsFeed = MutableLiveData(feed)
        Log.e("feed")
        titleLiveData = MutableLiveData(feed.title)

        if (DateUtils.getTripDisplay(feed.pubDate) != "")
            pubDateLiveData = MutableLiveData(DateUtils.getTripDisplay(feed.pubDate))
        else
            pubDateLiveData = MutableLiveData(feed.pubDate)
    }

    fun onCloseClick() {
        state.postValue(CommonState.BackClick)
    }

    fun onLinkClick(url: String) = viewModelScope.launch(dispatcher.default()) {
        Log.e("onLinkClick $url")
        state.postValue(CommonState.LinkClick(url))
    }
}