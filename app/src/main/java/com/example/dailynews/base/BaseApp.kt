package com.example.dailynews.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(
                AppComponents().apiModule
            )
        }
    }

    companion object {
        var appInstance: BaseApp? = null


    }
}