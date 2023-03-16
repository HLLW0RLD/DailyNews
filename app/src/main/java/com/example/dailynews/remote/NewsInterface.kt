package com.example.dailynews.remote

import com.example.dailynews.data.Articles
import com.example.dailynews.data.Categories
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "d3444f1232f54da0b192955bb3045aa9"
const val DEFAULT_Q = "news"
const val DEFAULT_COUNTRY = "us"
interface NewsInterface {

    @GET("everything")
    fun getAllNews(
        @Query("q") q: String = DEFAULT_Q,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Articles>

    @GET("everything")
    fun searchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Articles>

    @GET("top-headlines/")
    fun getNewsByCategory(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Articles>
}