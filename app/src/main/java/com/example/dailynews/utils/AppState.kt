package com.example.dailynews.utils

import com.example.dailynews.data.Articles

sealed class AppState{
    class Success(val data: Articles): AppState()
    object Loading: AppState()
}
