package com.example.newsapp.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.utils.constants.ApiConstants
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel( private val repository: NewsRepository) : ViewModel() {
    private val _newsArticlesState = MutableStateFlow(HomeUiState())
    val newsArticlesState = _newsArticlesState.asStateFlow()

    fun getArticles(){
        viewModelScope.launch {
            _newsArticlesState.value = _newsArticlesState.value.copy(
                isLoading = true,
            )
            try {
                val result = repository.getTopHeadLines(
                    apiKey = ApiConstants.NEWS_API_KEY,
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
}