package com.example.newsapp.data.remote

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.utils.constants.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(ApiConstants.TOP_HEADLINES_END_POINT)
    suspend fun getTopHeadLines(
        @Query("apiKey") apiKey : String,
        @Query("country") country : String
    ) : NewsResponse

    @GET(ApiConstants.EVERYTHING_END_POINT)
    suspend fun searchForNews(
        @Query("apiKey") apiKey : String,
        @Query("q") query : String
    ): NewsResponse
}