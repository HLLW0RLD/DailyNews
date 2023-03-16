package com.example.dailynews.service.domain

import com.example.dailynews.db.NewsEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ILocalService {
    fun insert(news: NewsEntity)

    fun getAll(): Observable<List<NewsEntity>>

    fun getNewsByTitle(title: String): Single<NewsEntity>

    fun delete(news: NewsEntity)
}