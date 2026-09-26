package com.example.newsapp.data.repository

import com.example.newsapp.data.local.ArticleDao
import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.local.toEntity
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NewsRepository(private val articleDao: ArticleDao? = null) {
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

    suspend fun searchForNews(
        apiKey: String,
        query : String
    ) : NewsResponse {
        return api.searchForNews(
            apiKey = apiKey ,
            query = query
        )
    }

    val favoriteArticles: Flow<List<ArticleEntity>> = articleDao?.getFavoriteArticles() ?: flowOf(emptyList())

    suspend fun insertFavorite(article: Article) {
        articleDao?.insertArticle(article.toEntity())
    }

    suspend fun deleteFavorite(article: Article) {
        articleDao?.deleteArticle(article.toEntity())
    }

    fun isFavorite(url: String): Flow<Boolean> {
        return articleDao?.isArticleFavorite(url) ?: flowOf(false)
    }
}
