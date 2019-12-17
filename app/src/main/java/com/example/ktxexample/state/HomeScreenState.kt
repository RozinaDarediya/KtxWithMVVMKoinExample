package com.example.ktxexample.state

import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.model.response.feed_model.RSS.Feed

sealed class HomeScreenState {

    object Initial : HomeScreenState()
    object Loading : HomeScreenState()

    data class ApiFailure(val throwable: Throwable) : HomeScreenState()
    data class FeedResponse(val list: ArrayList<BaseNewsFeed>) : HomeScreenState()
    data class LoadingState(val isLoading: Boolean) : HomeScreenState()

}

sealed class Resource<out T> {

    class Success<out T>(val data: T) : Resource<T>()
    class Error<out T>(throwable: Throwable) : Resource<T>()
    class Loading<out T> : Resource<T>()
}