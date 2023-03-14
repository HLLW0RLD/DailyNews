package com.example.dailynews.data

data class Articles(val status: String,
                    val totalResult: Int,
                    val articles: List<News>)
