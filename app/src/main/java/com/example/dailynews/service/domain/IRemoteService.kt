package com.example.dailynews.service.domain

import com.example.dailynews.data.Articles
import io.reactivex.rxjava3.core.Single

interface IRemoteService {
    fun getAllNews(): Single<Articles>

    fun getNewsByCategory(categories: String): Single<Articles>

    fun searchNews(q: String): Single<Articles>
}