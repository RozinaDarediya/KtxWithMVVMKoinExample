package com.example.ktxexample.base

import android.app.Application
import com.example.ktxexample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    coreModule,
                    vmModule,
                    networkModule,
                    devideInfo,
                    appDatabase,
                    RSSNetwork,
                    homeVmModule, detailVmModule
                )
            )
        }
    }
}