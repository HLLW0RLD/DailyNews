package com.example.dailynews.remote

import com.example.dailynews.data.Articles
import com.example.dailynews.data.Categories
import io.reactivex.rxjava3.core.Single

class NewsApi(private val web: NewsInterface) {

    fun getAllNews(): Single<Articles> {
        return web.getAllNews()
    }

    fun getNewsByCategory(categories: String): Single<Articles> {
        return web.getNewsByCategory(categories)
    }

    fun searchNews(q: String): Single<Articles> {
        return web.searchNews(q = q)
    }
}