package com.example.ktxexample.remote

object Api {

    const val AccessToken = "httpx-thetatech-accesstoken"

    const val BASE_URL = "https://dev.onetapcare.com:5002/"
    var baseURLForRSS: String = "https://news.google.com/rss/"
    var baseURLForInfermedica = "https://api.infermedica.com/"

    const val LOGIN_PATH = "api/adminLogin"

    const val get_news = "api/getNews"
    const val get_rss = "search?"
}