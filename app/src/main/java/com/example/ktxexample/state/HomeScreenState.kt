package com.example.ktxexample.state

import com.example.ktxexample.model.response.feed_model.BaseNewsFeed

sealed class HomeScreenState {

    object Initial : HomeScreenState()
    object Loading : HomeScreenState()

    data class ApiFailure(val throwable: Throwable) : HomeScreenState()
    data class FeedResponse(val list: ArrayList<BaseNewsFeed>) : HomeScreenState()
}