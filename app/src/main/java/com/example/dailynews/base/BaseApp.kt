package com.example.dailynews.base

import android.app.Application
import androidx.room.Room
import com.example.dailynews.db.NewsDAO
import com.example.dailynews.db.NewsDataBase
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

        private var db: NewsDataBase? = null
        private const val DB_NAME = "UserData.db"
        fun getUserDAO(): NewsDAO {
            if (db == null) {
                synchronized(NewsDataBase::class.java) {
                    if (db == null) {
                        appInstance?.let { app ->
                            db = Room.databaseBuilder(
                                app.applicationContext,
                                NewsDataBase::class.java,
                                DB_NAME
                            ).build()
                        }
                    }
                }
            }
            return db!!.newsData()
        }
    }
}