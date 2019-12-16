package com.example.ktxexample.remote

import com.example.ktxexample.model.response.feed_model.AdvisoryModel
import com.example.ktxexample.model.response.feed_model.RSS.Feed
import com.example.ktxexample.model.response.login.LoginResponseModel
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @POST(Api.LOGIN_PATH)
    @FormUrlEncoded
    suspend fun userLogin(@FieldMap partMap: Map<String, String>): LoginResponseModel

    @POST(Api.get_news)
    @FormUrlEncoded
    suspend fun getNews(@Header(Api.AccessToken) token: String, @Field("country") partMap: String): AdvisoryModel

    @POST(Api.get_news)
    @FormUrlEncoded
    suspend fun getNewsDeferred(@Header(Api.AccessToken) token: String, @Field("country") partMap: String):AdvisoryModel

}

interface ApiRSSService {

    @GET(Api.get_rss)
    suspend fun getData(@Query("q") str: String): Feed

    @GET(Api.get_rss)
    suspend fun getDataDeferred(@Query("q") str: String): Feed

}