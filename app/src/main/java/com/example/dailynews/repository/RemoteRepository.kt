package com.example.dailynews.repository

import com.example.dailynews.remote.NewsApi
import com.example.dailynews.repository.domain.IRemoteRepository

class RemoteRepository(private val api: NewsApi): IRemoteRepository {


}