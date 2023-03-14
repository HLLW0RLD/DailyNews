package com.example.dailynews.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailynews.data.Categories
import com.example.dailynews.service.domain.IRemoteService
import com.example.dailynews.utils.AppState
import com.example.dailynews.utils.Constants.MAIN_NEWS_TAG
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainNewsViewModel: ViewModel(), KoinComponent {

    val newsData: MutableLiveData<AppState> = MutableLiveData()
    private val remoteService: IRemoteService by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getAllNews() {
        newsData.value = AppState.Loading
        disposable.add(
            remoteService
                .getAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        newsData.postValue(
                            AppState.Success(it)
                        )
                        Log.d(MAIN_NEWS_TAG, "mv getAllNews() -> $it")
                    },
                    onError = {
                        Log.d(MAIN_NEWS_TAG, "e -> $it")
                    }
                )
        )
    }

    fun searchNews(q: String) {
        newsData.value = AppState.Loading
        disposable.add(
            remoteService
                .searchNews(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        newsData.postValue(
                            AppState.Success(it)
                        )
                        Log.d(MAIN_NEWS_TAG, "mv searchNews() key: $q -> $it")
                    },
                    onError = {
                        Log.d(MAIN_NEWS_TAG, "e -> $it")
                    }
                )
        )
    }

    fun getNewsByCategory(categories: Categories) {
        newsData.value = AppState.Loading
        disposable.add(
            remoteService
                .getNewsByCategory(categories.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        newsData.postValue(
                            AppState.Success(it)
                        )
                        Log.d(MAIN_NEWS_TAG, "mv getAllNews() category: $categories -> $it")
                    },
                    onError = {
                        Log.d(MAIN_NEWS_TAG, "e -> $it")
                    }
                )
        )
    }
}