package com.example.dailynews.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailynews.data.Categories
import com.example.dailynews.data.News
import com.example.dailynews.db.NewsEntity
import com.example.dailynews.repository.domain.ILocalRepository
import com.example.dailynews.repository.domain.IRemoteRepository
import com.example.dailynews.utils.AppState
import com.example.dailynews.utils.Constants.MAIN_NEWS_TAG
import com.example.dailynews.utils.Helper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class MainNewsViewModel: ViewModel(), KoinComponent {

    val newsData: MutableLiveData<AppState> = MutableLiveData()
    private val remote: IRemoteRepository by inject()
    private val local: ILocalRepository by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getAllNews() {
        newsData.value = AppState.Loading
        disposable.add(
            remote
                .getAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        newsData.postValue(
                            AppState.Success(it.articles)
                        )
                        Log.d(MAIN_NEWS_TAG, "nv getAllNews() -> $it")
                    },
                    onError = {
                        Log.d(MAIN_NEWS_TAG, "vm getAllNews() \ne -> $it")
                    }
                )
        )
    }

    fun searchNews(q: String) {
        newsData.value = AppState.Loading
        disposable.add(
            remote
                .searchNews(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        newsData.postValue(
                            AppState.Success(it.articles)
                        )
                        Log.d(MAIN_NEWS_TAG, "vm searchNews() key: $q -> $it")
                    },
                    onError = {
                        Log.d(MAIN_NEWS_TAG, "vm searchNews() key: $q \ne -> $it")
                    }
                )
        )
    }

    fun getNewsByCategory(categories: Categories) {
        newsData.value = AppState.Loading
        disposable.add(
            remote
                .getNewsByCategory(categories.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        newsData.postValue(
                            AppState.Success(it.articles)
                        )
                        Log.d(MAIN_NEWS_TAG, "vm getAllNews() category: $categories -> $it")
                    },
                    onError = {
                        Log.d(MAIN_NEWS_TAG, "vm getAllNews() category: $categories \ne -> $it")
                    }
                )
        )
    }

    private fun saveToDB(news: News){
        Single.just(news)
            .subscribeOn(Schedulers.io())
            .toObservable()
            .subscribeBy (
                onNext = {
                    val entity = NewsEntity(
                        id = 0,
                        source = news.source.name,
                        author = news.author,
                        title = news.title,
                        description = news.description,
                        url = news.url,
                        urlToImage = news.urlToImage,
                        publishedAt = news.publishedAt,
                        content = news.content,
                        timestamp = Date().time
                    )
                    local.insert(entity)
                    Log.d(MAIN_NEWS_TAG, "vm saveToDB() -> $entity")
                },
                onError = {
                    Log.d(MAIN_NEWS_TAG, "vm saveToDB() -> $it")
                }
            )
    }

    fun saveToFavorites(news: News) {
        disposable.add(
            local
                .getNewsByTitle(news.title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        Helper.toastShort("News already saved")
                    },
                    onError = {
                        saveToDB(news)
                        Helper.toastShort("Added to favorite")
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}