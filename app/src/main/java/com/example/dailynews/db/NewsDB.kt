package com.example.dailynews.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 4, exportSchema = false)
abstract class NewsDB: RoomDatabase() {
    abstract fun newsData(): NewsDAO
}