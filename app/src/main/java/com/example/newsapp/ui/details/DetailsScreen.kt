package com.example.newsapp.ui.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.ui.viewmodels.NewsViewModelFactory
import com.example.newsapp.ui.theme.PlusJakartaSans
import com.example.newsapp.ui.theme.Primary
import com.example.newsapp.ui.theme.Secondary
import com.example.newsapp.ui.theme.TextPrimary
import com.example.newsapp.ui.theme.TextSecondary
import com.example.newsapp.ui.viewmodels.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(article: Article, navController: NavHostController) {
    val context = LocalContext.current
    val repository = remember {
        val dao = NewsDatabase.getDatabase(context).articleDao()
        NewsRepository(dao)
    }
    val viewModel: NewsViewModel = viewModel(
        factory = NewsViewModelFactory(repository)
    )
    val favoriteArticles by viewModel.favoriteArticles.collectAsState()

    val isFavorite = favoriteArticles.any {
        it.url == article.url
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Article Details",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Primary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    IconButton(onClick = {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "${article.title}\n${article.url}")
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share",
                            tint = Primary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(onClick = {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                        context.startActivity(browserIntent)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Open in Browser",
                            tint = Primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (!article.urlToImage.isNullOrEmpty()) {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                text = article.title,
                fontSize = 22.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = article.publishedAt.substring(0, minOf(10, article.publishedAt.length)),
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    color = Color.Gray
                )
            }

            if (!article.author.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "By ${article.author}",
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.description ?: "",
                fontSize = 16.sp,
                fontFamily = PlusJakartaSans,
                color = TextSecondary,
                lineHeight = 24.sp
            )

            if (!article.content.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = article.content,
                    fontSize = 16.sp,
                    fontFamily = PlusJakartaSans,
                    color = TextPrimary,
                    lineHeight = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.toggleFavorite(article)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFavorite) Color.Gray else Primary,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .padding(vertical = 22.dp)
                    .fillMaxWidth()
                    .height(54.dp)
                    .shadow(
                        elevation = 7.dp,
                        shape = RoundedCornerShape(18.dp),
                        spotColor = Secondary
                    )
            ) {
                Text(
                    text = if (isFavorite) "Remove from Favorites" else "Save To Favorites",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
