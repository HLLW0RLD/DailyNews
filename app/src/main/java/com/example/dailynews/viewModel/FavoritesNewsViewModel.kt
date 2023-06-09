package com.example.dailynews.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailynews.data.News
import com.example.dailynews.db.NewsEntity
import com.example.dailynews.repository.domain.ILocalRepository
import com.example.dailynews.utils.AppState
import com.example.dailynews.utils.Constants
import com.example.dailynews.utils.Helper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavoritesNewsViewModel : ViewModel(), KoinComponent {

    val newsData: MutableLiveData<AppState> = MutableLiveData()
    private val local: ILocalRepository by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getFavoritesNews() {
        newsData.value = AppState.Loading
        disposable.add(
            local
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        newsData.postValue(
                            AppState.Success(Helper.convertEntitiesToModel(it))
                        )
                        Log.d(Constants.FAVORITES_NEWS_TAG, "vm getFavoritesNews() -> $it")
                    },
                    onError = {
                        Log.d(Constants.FAVORITES_NEWS_TAG, "vm getFavoritesNews() \ne -> $it")
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}