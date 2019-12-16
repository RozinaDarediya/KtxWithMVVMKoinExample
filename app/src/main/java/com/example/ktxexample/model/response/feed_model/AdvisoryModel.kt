package com.example.ktxexample.model.response.feed_model

import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.google.gson.annotations.SerializedName

data class AdvisoryModel(

    @SerializedName("errorMsg")
    val errorMsg: String,

    @SerializedName("response")
    val response: Response

) {
    data class Response(

        @SerializedName("healthNews")
        var healthNews: List<BaseNewsFeed>,

        @SerializedName("travelNews")
        val travelNews: List<BaseNewsFeed>
    ) {

        //class HealthNews : BaseNewsFeed()
        class HealthNews : BaseNewsFeed()

        class TravelNews : BaseNewsFeed()
    }
}