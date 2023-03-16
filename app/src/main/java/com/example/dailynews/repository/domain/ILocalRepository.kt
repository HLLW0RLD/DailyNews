package com.example.dailynews.repository.domain

import com.example.dailynews.data.Articles
import com.example.dailynews.db.NewsEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ILocalRepository {
    fun insert(news: NewsEntity)

    fun getAll(): Observable<List<NewsEntity>>

    fun getNewsByTitle(title: String): Single<NewsEntity>

    fun delete(news: NewsEntity)
}