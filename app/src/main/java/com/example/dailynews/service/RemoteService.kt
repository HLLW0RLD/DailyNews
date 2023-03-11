package com.example.dailynews.service

import com.example.dailynews.data.Articles
import com.example.dailynews.data.Categories
import com.example.dailynews.repository.domain.IRemoteRepository
import com.example.dailynews.service.domain.IRemoteService
import io.reactivex.rxjava3.core.Single

class RemoteService(private val remote: IRemoteRepository): IRemoteService {

    override fun getAllNews(): Single<Articles> {
        return remote.getAllNews()
    }

    override fun getNewsByCategory(categories: Categories): Single<Articles> {
        return remote.getNewsByCategory(categories)
    }

    override fun searchNews(q: String): Single<Articles> {
        return remote.searchNews(q = q)
    }
}