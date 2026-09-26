package com.example.newsapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.ui.components.ArticleCard
import com.example.newsapp.ui.viewmodels.NewsViewModelFactory
import com.example.newsapp.ui.theme.PlusJakartaSans
import com.example.newsapp.ui.theme.Primary
import com.example.newsapp.ui.theme.TextFieldBackground
import com.example.newsapp.ui.theme.TextFieldIcon
import com.example.newsapp.ui.theme.TextFieldText
import com.example.newsapp.ui.theme.TextPrimary
import com.example.newsapp.ui.viewmodels.NewsViewModel

@Composable
fun SearchScreen(
    onArticleClick: (Article) -> Unit
) {
    var query by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val repository = remember {
        val dao = NewsDatabase.getDatabase(context).articleDao()
        NewsRepository(dao)
    }

    val viewModel: NewsViewModel = viewModel(
        factory = NewsViewModelFactory(repository)
    )

    val state by viewModel.searchState.collectAsState()
    val favoriteArticles by viewModel.favoriteArticles.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {

        TextField(
            value = query,
            onValueChange = {
                query = it
            },
            textStyle = TextStyle(
                color = TextFieldText
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(color = TextFieldBackground),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    tint = TextFieldIcon
                )
            },
            placeholder = {
                Text(
                    text = "Search for news...",
                    color = TextFieldIcon
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackground,
                unfocusedContainerColor = TextFieldBackground,
                disabledContainerColor = TextFieldBackground,
                errorContainerColor = TextFieldBackground,

                focusedIndicatorColor = Primary,
                unfocusedIndicatorColor = TextFieldBackground,
                cursorColor = Primary
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (query.isNotBlank()) {
                        viewModel.searchForNews(query)
                    }
                }
            ), singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            )
        )

        if (state.isLoading) {

            Text(
                text = "Searching...",
                modifier = Modifier.padding(16.dp)
            )

        } else if (state.articles.isEmpty()) {

            Text(
                text = "No articles found",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    Text(
                        text = "Search Results ${state.articles.size}",
                        color = TextPrimary,
                        fontFamily = PlusJakartaSans,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(
                    items = state.articles,
                    key = { it.url }
                ) { article ->
                    ArticleCard(
                        article = article,
                        isFavorite = favoriteArticles.any {
                            it.url == article.url
                        },
                        onFavoriteClick = {
                            viewModel.toggleFavorite(article)
                        },
                        onClick = {
                            onArticleClick(article)
                        }
                    )
                }
            }
        }
    }
}
