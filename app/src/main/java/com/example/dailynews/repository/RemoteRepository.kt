package com.example.dailynews.repository

import com.example.dailynews.data.Articles
import com.example.dailynews.data.Categories
import com.example.dailynews.remote.NewsApi
import com.example.dailynews.repository.domain.IRemoteRepository
import io.reactivex.rxjava3.core.Single

class RemoteRepository(private val api: NewsApi): IRemoteRepository {

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