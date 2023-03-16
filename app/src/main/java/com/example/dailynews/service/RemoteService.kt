package com.example.dailynews.service

import com.example.dailynews.data.Articles
import com.example.dailynews.remote.NewsApi
import com.example.dailynews.service.domain.IRemoteService
import io.reactivex.rxjava3.core.Single

class RemoteService(private val api: NewsApi): IRemoteService {

    override fun getAllNews(): Single<Articles> {
        return api.getAllNews()
    }

    override fun getNewsByCategory(categories: String): Single<Articles> {
        return api.getNewsByCategory(categories)
    }

    override fun searchNews(q: String): Single<Articles> {
        return api.searchNews(q = q)
    }
}