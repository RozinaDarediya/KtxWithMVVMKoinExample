package com.example.ktxexample.di

import com.example.ktxexample.local.AppDatabase
import com.example.ktxexample.model.request.DeviceInfo
import com.example.ktxexample.remote.Api
import com.example.ktxexample.remote.ApiRSSService
import com.example.ktxexample.remote.ApiService
import com.example.ktxexample.remote.HttpClientService
import com.example.ktxexample.utils.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("HttpLoggingInterceptor $message")
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>()).build()
    }

    single {
        Log.e("Retrofit.Builder() networkModule")
        Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().setPrettyPrinting().create()))
            .build()
            .create(ApiService::class.java)
    }

    /* single {
         Log.e("Retrofit.RxJava2CallAdapterFactory")
         Retrofit.Builder()
             .baseUrl(Api.baseURLForInfermedica)
             .client(get())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().setPrettyPrinting().create()))
             .build()
     }*/
}

val RSSNetwork = module {
    single {
        Retrofit.Builder()
            .baseUrl(Api.baseURLForRSS)
            .client(get())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(HttpClientService.getUnsafeOkHttpClient())
            .build()
            .create(ApiRSSService::class.java)
    }
}

val devideInfo = module {
    single {
        DeviceInfo(get())
    }
}

val appDatabase = module {
    Log.e("appDatabase Module")
    single {
        AppDatabase.getAppDataBase(get())
    }
}