package com.example.dailynews.repository.domain

import com.example.dailynews.data.Articles
import com.example.dailynews.data.Categories
import io.reactivex.rxjava3.core.Single

interface IRemoteRepository {
    fun getAllNews(): Single<Articles>

    fun getNewsByCategory(categories: String): Single<Articles>

    fun searchNews(q: String): Single<Articles>
}