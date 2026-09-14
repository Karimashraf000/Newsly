package com.example.newsapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.example.newsapp.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.newsapp.ui.theme.TextSecondary
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { MainTopAppBar() },
        bottomBar = { MainBottomAppBar(navController) }) { innerPadding ->

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
fun MainTopAppBar(
    hasUnreadNotifications: Boolean = true,
    onNotificationClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Newsly",
                fontFamily = PlayfairDisplay,
                color = Primary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            Surface(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(38.dp),
                shape = RoundedCornerShape(12.dp),
                color = Primary.copy(alpha = 0.1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.newsly_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.padding(7.dp)
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .size(38.dp)
                        .clickable { onNotificationClick() },
                    shape = RoundedCornerShape(12.dp),
                    color = Primary.copy(alpha = 0.1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            tint = Primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                if (hasUnreadNotifications) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-2).dp, y = 2.dp)
                            .size(9.dp)
                            .background(color = Color(0xFFE53935), shape = CircleShape)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}


@Composable
fun MainBottomAppBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(30.dp)
            ).padding(8.dp)
    ) {
        NavigationBar(
            modifier = Modifier
                .padding(0.dp)
                .border(0.dp, Primary, shape = RoundedCornerShape(30.dp))
                .wrapContentHeight()
        ) {
            NavigationBarItem(
                selected = currentRoute == "home", onClick = {
                    navController.navigate("home")
                }, icon = {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home",
                    )
                }, label = {
                    Text("Home")
                }, colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = Primary,

                    selectedTextColor = Primary,

                    indicatorColor = Primary.copy(
                        alpha = 0.12f
                    ), unselectedIconColor = TextSecondary, unselectedTextColor = TextSecondary
                )
            )

            NavigationBarItem(
                selected = currentRoute == "search", onClick = {
                    navController.navigate("search")
                }, icon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                    )
                }, label = {
                    Text("Search")
                }, colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = Primary,

                    selectedTextColor = Primary,

                    indicatorColor = Primary.copy(
                        alpha = 0.12f
                    ), unselectedIconColor = TextSecondary, unselectedTextColor = TextSecondary
                )
            )

            NavigationBarItem(
                selected = currentRoute == "bookMarks", onClick = {
                    navController.navigate("bookMarks")
                }, icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "bookMarks",
                    )
                }, label = {
                    Text("BookMarks")
                }, colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = Primary,

                    selectedTextColor = Primary,

                    indicatorColor = Primary.copy(
                        alpha = 0.12f
                    ), unselectedIconColor = TextSecondary, unselectedTextColor = TextSecondary
                )
            )
        }
    }
}