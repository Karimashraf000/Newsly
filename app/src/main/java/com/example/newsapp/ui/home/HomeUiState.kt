package com.example.newsapp.ui.home

import com.example.newsapp.data.model.Article

data class HomeUiState(
    val isLoading : Boolean = false,
    val articles : List<Article> = emptyList(),
    val error : String? = null
)