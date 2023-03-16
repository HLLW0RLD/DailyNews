package com.example.dailynews.service

import com.example.dailynews.db.NewsDAO
import com.example.dailynews.db.NewsEntity
import com.example.dailynews.service.domain.ILocalService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class LocalService(private val dao: NewsDAO): ILocalService {

    override fun insert(news: NewsEntity) {
        dao.insert(news)
    }

    override fun getAll(): Observable<List<NewsEntity>> {
        return dao.all()
    }

    override fun getNewsByTitle(title: String): Single<NewsEntity> {
        return dao.getNewsByTitle(title)
    }

    override fun delete(news: NewsEntity) {
        dao.delete(news)
    }
}