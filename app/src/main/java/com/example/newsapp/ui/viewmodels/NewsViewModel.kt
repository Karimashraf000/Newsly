package com.example.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.local.toArticle
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.ui.home.HomeUiState
import com.example.newsapp.ui.search.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _newsArticlesState = MutableStateFlow(HomeUiState())
    val newsArticlesState = _newsArticlesState.asStateFlow()

    private val _searchState = MutableStateFlow(SearchUiState())
    val searchState = _searchState.asStateFlow()

    val favoriteArticles: StateFlow<List<Article>> =
        repository.favoriteArticles
            .map { list ->
                list.map { it.toArticle() }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun toggleFavorite(article: Article) {
        viewModelScope.launch {

            val isFavorite = favoriteArticles.value.any {
                it.url == article.url
            }

            if (isFavorite) {
                repository.deleteFavorite(article)
            } else {
                repository.insertFavorite(article)
            }
        }
    }

    fun getArticles(){
        viewModelScope.launch {
            _newsArticlesState.value = _newsArticlesState.value.copy(
                isLoading = true,
            )
            try {
                val result = repository.getTopHeadLines(
                    apiKey = BuildConfig.NEWS_API_KEY,
                    country = "us"
                )
                _newsArticlesState.value =  _newsArticlesState.value.copy(
                    isLoading = false,
                    articles = result.articles
                )
            }catch (e: Exception) {
                e.printStackTrace()
                _newsArticlesState.value = _newsArticlesState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
    fun searchForNews(query: String) {
        viewModelScope.launch {

            _searchState.value = _searchState.value.copy(
                isLoading = true,
            )
            try {
                val result = repository.searchForNews(
                    apiKey = BuildConfig.NEWS_API_KEY,
                    query = query
                )
                _searchState.value =  _searchState.value.copy(
                    isLoading = false,
                    articles = result.articles
                )
            }catch (e: Exception) {
                e.printStackTrace()
                _searchState.value = _searchState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
