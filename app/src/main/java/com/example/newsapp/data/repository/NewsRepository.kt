package com.example.newsapp.data.repository


import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.remote.RetrofitInstance

class NewsRepository {
    private val api = RetrofitInstance.api

    suspend fun getTopHeadLines(
        apiKey: String,
        country : String
    ) : NewsResponse {
        return api.getTopHeadLines(
            apiKey = apiKey ,
            country = country
        )
    }
}