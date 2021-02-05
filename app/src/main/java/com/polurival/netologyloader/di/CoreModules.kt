package com.polurival.netologyloader.di

import com.polurival.netologyloader.data.service.NetologyRepository
import com.polurival.netologyloader.data.service.NetologyService
import com.polurival.netologyloader.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Польщиков Юрий on 05/02/2021
 */

@JvmField
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://gitcdn.link/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { NetologyService(get()) }
    single { Dispatchers.Default }
}

@JvmField
val repositoryModule = module {
    single { NetologyRepository(get(), get()) }
}

@JvmField
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}