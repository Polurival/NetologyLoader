package com.polurival.netologyloader

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.polurival.netologyloader.di.networkModule
import com.polurival.netologyloader.di.repositoryModule
import com.polurival.netologyloader.di.viewModelModule

/**
 * @author Польщиков Юрий on 05/02/2021
 */
class NetologyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NetologyApp)
            androidLogger()
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}