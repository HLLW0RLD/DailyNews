package com.example.dailynews.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface NewsDAO {

    @Insert(onConflict = IGNORE)
    fun insert(entity: NewsEntity)

    @Query("SELECT * FROM NewsEntity")
    fun all(): Observable<List<NewsEntity>>

    @Query("SELECT * FROM NewsEntity WHERE title LIKE :title")
    fun getNewsByTitle(title: String): Single<NewsEntity>

    @Delete
    fun delete(entity: NewsEntity)
}