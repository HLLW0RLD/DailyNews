package com.example.dailynews.repository

import com.example.dailynews.data.Articles
import com.example.dailynews.db.NewsEntity
import com.example.dailynews.service.domain.ILocalService
import com.example.dailynews.repository.domain.ILocalRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class LocalRepository(private val local: ILocalService): ILocalRepository {

    override fun insert(news: NewsEntity) {
        local.insert(news)
    }

    override fun getAll(): Observable<List<NewsEntity>> {
        return local.getAll()
    }

    override fun getNewsByTitle(title: String): Single<NewsEntity> {
        return local.getNewsByTitle(title)
    }

    override fun delete(news: NewsEntity) {
        local.delete(news)
    }
}