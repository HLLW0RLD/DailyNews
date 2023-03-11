package com.example.dailynews.data

data class Articles(val status: String,
                    val totalResult: Int,
                    val sources: List<News>)
