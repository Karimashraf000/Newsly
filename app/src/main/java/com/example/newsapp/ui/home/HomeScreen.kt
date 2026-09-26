package com.example.newsapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.ui.components.ArticleCard
import com.example.newsapp.ui.components.TrendingNewsCarousel
import com.example.newsapp.ui.theme.PlusJakartaSans
import com.example.newsapp.ui.theme.TextPrimary
import com.example.newsapp.ui.viewmodels.NewsViewModelFactory
import com.example.newsapp.ui.viewmodels.NewsViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, onArticleClick: (Article) -> Unit
) {
    val context = LocalContext.current
    val repository = remember {
        val dao = NewsDatabase.getDatabase(context).articleDao()
        NewsRepository(dao)
    }

    val viewModel: NewsViewModel = viewModel(
        factory = NewsViewModelFactory(repository)
    )

    val state by viewModel.newsArticlesState.collectAsState()
    val favoriteArticles by viewModel.favoriteArticles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getArticles()
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error ?: "Unknown error",
                        color = TextPrimary,
                        fontFamily = PlusJakartaSans
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Column {
                            TrendingNewsCarousel(
                                items = state.articles, onArticleClick = onArticleClick
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Breaking News",
                                color = TextPrimary,
                                fontFamily = PlusJakartaSans,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    items(
                        items = state.articles, key = { it.url }) { article ->
                        ArticleCard(article = article, isFavorite = favoriteArticles.any {
                            it.url == article.url
                        }, onFavoriteClick = {
                            viewModel.toggleFavorite(article)
                        }, onClick = { onArticleClick(article) })
                    }
                }
            }
        }
    }
}
