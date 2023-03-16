package com.example.dailynews.base

import com.example.dailynews.db.NewsDAO
import com.example.dailynews.remote.NewsApi
import com.example.dailynews.remote.NewsInterface
import com.example.dailynews.service.LocalService
import com.example.dailynews.service.domain.IRemoteService
import com.example.dailynews.service.RemoteService
import com.example.dailynews.service.domain.ILocalService
import com.example.dailynews.repository.domain.ILocalRepository
import com.example.dailynews.repository.domain.IRemoteRepository
import com.example.dailynews.repository.LocalRepository
import com.example.dailynews.repository.RemoteRepository
import com.example.dailynews.viewModel.FavoritesNewsViewModel
import com.example.dailynews.viewModel.MainNewsViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val URL = "https://newsapi.org/v2/"
class AppComponents {

    val apiModule = module {

        viewModel<FavoritesNewsViewModel> {
            FavoritesNewsViewModel()
        }

        viewModel<MainNewsViewModel> {
            MainNewsViewModel()
        }

        single<IRemoteRepository> {
            RemoteRepository(get())
        }

        single<ILocalRepository> {
            LocalRepository(get())
        }

        single<IRemoteService> {
            RemoteService(NewsApi(get()))
        }

        single<NewsInterface> {
            get<Retrofit>()
                .create(NewsInterface::class.java)
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(get())
                .build()
        }
        factory<Converter.Factory> {
            GsonConverterFactory
                .create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
        }

        single<ILocalService> {
            LocalService(get())
        }

        single<NewsDAO> {
            BaseApp.newsDAO()
        }
    }
}