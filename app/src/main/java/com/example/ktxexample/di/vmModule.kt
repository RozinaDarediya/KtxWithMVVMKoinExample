package com.example.ktxexample.di

import com.example.ktxexample.viewmodel.DetailActivityVM
import com.example.ktxexample.viewmodel.HomeActivityVM
import com.example.ktxexample.viewmodel.LoginActivityVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel {
        LoginActivityVM()
    }
}

val homeVmModule = module {
    viewModel {
        HomeActivityVM()
    }
}

val detailVmModule = module {
    viewModel {
        DetailActivityVM()
    }
}
