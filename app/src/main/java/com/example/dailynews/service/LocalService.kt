package com.example.dailynews.service

import com.example.dailynews.repository.domain.ILocalRepository
import com.example.dailynews.service.domain.ILocalService

class LocalService(private val repository: ILocalRepository): ILocalService {
}