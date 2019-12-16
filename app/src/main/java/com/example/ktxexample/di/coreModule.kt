package com.example.ktxexample.di

import android.content.Context
import android.util.Patterns
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module
import java.util.regex.Pattern

val coreModule = module {

    single {
        androidApplication().getSharedPreferences("app_pref", Context.MODE_PRIVATE)
    }

    single<Pattern> {
        Patterns.EMAIL_ADDRESS
    }

}