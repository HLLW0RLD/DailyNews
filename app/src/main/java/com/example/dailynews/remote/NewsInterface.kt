package com.example.dailynews.remote

import com.example.dailynews.data.Articles
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "d3444f1232f54da0b192955bb3045aa9"
interface NewsInterface {

    @GET("/everything")
    fun getAllNews(
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Articles>

    @GET("/top-headlines")
    fun getTopNews(
        @Query("category") category: String = API_KEY ,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Articles>
}