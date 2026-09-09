package com.example.newsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.example.newsapp.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.bookmarks.BookmarkedNewsScreen
import com.example.newsapp.ui.home.HomeScreen
import com.example.newsapp.ui.search.SearchScreen
import com.example.newsapp.ui.theme.PlayfairDisplay
import com.example.newsapp.ui.theme.Primary

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { MainTopAppBar() },
        bottomBar = { MainBottomAppBar(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen()
            }

            composable("search") {
                SearchScreen()
            }

            composable("bookmarks") {
                BookmarkedNewsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Newsly",
                fontFamily = PlayfairDisplay,
                color = Primary,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.newsly_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(35.dp).padding(5.dp)
            )
        },
        actions = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search",
                tint = Primary,
                modifier = Modifier.padding(5.dp)
            )
        }
        )
}

@Composable
fun MainBottomAppBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home")
            },
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                )
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "search",
            onClick = {
                navController.navigate("search")
            },
            icon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                )
            },
            label = {
                Text("Search")
            }
        )
        NavigationBarItem(
            selected = currentRoute == "bookMarks",
            onClick = {
                navController.navigate("bookMarks")
            },
            icon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "bookMarks",
                )
            },
            label = {
                Text("bookMarks")
            }
        )
    }
}
