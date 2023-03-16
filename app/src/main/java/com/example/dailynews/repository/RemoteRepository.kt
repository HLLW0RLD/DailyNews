package com.example.dailynews.repository

import com.example.dailynews.data.Articles
import com.example.dailynews.service.domain.IRemoteService
import com.example.dailynews.repository.domain.IRemoteRepository
import io.reactivex.rxjava3.core.Single

class RemoteRepository(private val remote: IRemoteService): IRemoteRepository {

    override fun getAllNews(): Single<Articles> {
        return remote.getAllNews()
    }

    override fun getNewsByCategory(categories: String): Single<Articles> {
        return remote.getNewsByCategory(categories)
    }

    override fun searchNews(q: String): Single<Articles> {
        return remote.searchNews(q = q)
    }
}