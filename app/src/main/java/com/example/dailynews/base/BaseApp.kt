package com.example.dailynews.base

import android.app.Application
import androidx.room.Room
import com.example.dailynews.db.NewsDAO
import com.example.dailynews.db.NewsDB
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

        private var db: NewsDB? = null
        private const val DB_NAME = "NewsData.db"
        fun newsDAO(): NewsDAO {
            if (db == null) {
                synchronized(NewsDB::class.java) {
                    if (db == null) {
                        appInstance?.let { app ->
                            db = Room.databaseBuilder(
                                app.applicationContext,
                                NewsDB::class.java,
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