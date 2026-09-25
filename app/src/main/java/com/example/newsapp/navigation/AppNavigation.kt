package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.model.Article
import com.example.newsapp.ui.details.DetailsScreen
import com.example.newsapp.ui.onboarding.OnboardingScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding"){
        composable("onboarding"){
            OnboardingScreen(
                onFinished = {
                    navController.navigate("main") {
                        popUpTo("onboarding") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable("main") {
            MainScreen(
                onArticleClick = { article ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                    navController.navigate("details")
                }
            )
        }
        composable("details") {
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
            if (article != null) {
                DetailsScreen(article = article, navController = navController)
            }
        }
    }
}
