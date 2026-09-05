package com.example.newsapp.data.model

data class NewsResponse(
    val status : String?,
    val totalResults : Int?,
    val articles : List<Article>
)