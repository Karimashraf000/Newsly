package com.example.newsapp.ui.search

import com.example.newsapp.data.model.Article

data class SearchUiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)