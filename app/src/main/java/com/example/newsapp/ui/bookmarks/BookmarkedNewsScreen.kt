package com.example.newsapp.ui.bookmarks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.newsapp.ui.viewmodels.NewsViewModelFactory
import com.example.newsapp.ui.theme.PlusJakartaSans
import com.example.newsapp.ui.theme.TextPrimary
import com.example.newsapp.ui.viewmodels.NewsViewModel

@Composable
fun BookmarkedNewsScreen(
    onArticleClick: (Article) -> Unit
) {
    val context = LocalContext.current
    val repository = remember {
        val dao = NewsDatabase.getDatabase(context).articleDao()
        NewsRepository(dao)
    }
    val viewModel: NewsViewModel = viewModel(
        factory = NewsViewModelFactory(repository)
    )

    val favoriteArticles by viewModel.favoriteArticles.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorite Articles",
            color = TextPrimary,
            fontFamily = PlusJakartaSans,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (favoriteArticles.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No favorite articles saved yet.",
                    color = TextPrimary,
                    fontFamily = PlusJakartaSans,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = favoriteArticles,
                    key = { it.url }
                ) { article ->
                    ArticleCard(
                        article = article,
                        isFavorite = favoriteArticles.any {
                            it.url == article.url
                        },
                        onFavoriteClick = { viewModel.toggleFavorite(article) },
                        onClick = { onArticleClick(article) }
                    )
                }
            }
        }
    }
}
