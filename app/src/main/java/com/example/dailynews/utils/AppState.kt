package com.example.dailynews.utils

import com.example.dailynews.data.Articles
import com.example.dailynews.data.News

sealed class AppState{
    class Success(val data: List<News>): AppState()
    object Loading: AppState()
}
