package com.example.dailynews.repository

import com.example.dailynews.db.NewsDAO
import com.example.dailynews.repository.domain.ILocalRepository

class LocalRepository(val dao: NewsDAO): ILocalRepository {


}