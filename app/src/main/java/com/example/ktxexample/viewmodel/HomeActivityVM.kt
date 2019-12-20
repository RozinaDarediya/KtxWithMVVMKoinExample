package com.example.ktxexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.ktxexample.app_interface.DefaultDispatcherProvider
import com.example.ktxexample.app_interface.DispatcherProvider
import com.example.ktxexample.base.BaseVM
import com.example.ktxexample.local.AppDatabase
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.model.response.feed_model.RSS.Feed
import com.example.ktxexample.remote.ApiRSSService
import com.example.ktxexample.state.HomeScreenState
import com.example.ktxexample.utils.AppConstants
import com.example.ktxexample.utils.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.inject

class HomeActivityVM(private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()) :
    BaseVM() {

    private val apiRssService: ApiRSSService by inject()
    private val appDatabase: AppDatabase by inject()

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

    private fun computeResult(result1: Feed?, result2: Feed?): ArrayList<BaseNewsFeed> {
        val list: ArrayList<Feed> = ArrayList()
        val baseNewsFeedList: ArrayList<BaseNewsFeed> = ArrayList()
        result1?.let { list.add(it) }
        result2?.let { list.add(it) }
        if (result1 != null) {
            val list1: List<BaseNewsFeed> = result1.articleList
            if (result1.articleList.size > 0) {
                for (i in 0 until list.size) {
                    list1[i].mainType = AppConstants.FEED_HEALTH
                    list1[i].feedPubDate =
                        com.example.ktxexample.utils.DateUtils.getRSSDate(list1[i].pubDate)
                    list1[i].country = "India"
                    //list1[i].imgContent.url = "https://microhealth.com/assets/images/illustrations/personal-user-illustration-@2x.png"
                }
            }
            baseNewsFeedList.addAll(list1)
        }
        if (result2 != null) {
            val list2: List<BaseNewsFeed> = result2.articleList
            if (result2.articleList.size > 0) {
                for (i in 0 until list.size) {
                    list2[i].mainType = AppConstants.FEED_HEALTH
                    list2[i].feedPubDate =
                        com.example.ktxexample.utils.DateUtils.getRSSDate(list2[i].pubDate)
                    list2[i].country = "India"
                    //list2[i].imgContent.url = "https://microhealth.com/assets/images/illustrations/personal-user-illustration-@2x.png"
                }
            }
            baseNewsFeedList.addAll(list2)
        }
        appDatabase.newsFeedDao().deleteTableRecords()
        Log.e("inside store " + appDatabase.newsFeedDao().getFeedRecords())
        appDatabase.newsFeedDao().insertRecordList(baseNewsFeedList)
        Log.e(appDatabase.newsFeedDao().getFeedRecords().size.toString())
        return baseNewsFeedList
    }

}