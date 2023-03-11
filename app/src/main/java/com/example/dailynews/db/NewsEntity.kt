package com.example.dailynews.db

import androidx.room.Entity
import com.example.dailynews.data.Source

@Entity
data class NewsEntity(val source: Source,
                      val author: String?,
                      val title: String,
                      val description: String?,
                      val url: String,
                      val urlToImage: String,
                      val publishedAt: String,
                      val content: String)