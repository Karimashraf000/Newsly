package com.example.newsapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.data.repository.NewsRepository

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val repository = remember {
        NewsRepository()
    }

    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(repository)
    )

    val state by viewModel.newsArticlesState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getArticles()
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(
                    text = state.error ?: "Unknown error"
                )
            }

            else -> {
                Text(
                    text = "Articles count: ${state.articles.size}"
                )

                LazyColumn {
                    items(state.articles) { article ->
                        Text(
                            text = article.title
                        )
                    }
                }
            }
        }
    }
}