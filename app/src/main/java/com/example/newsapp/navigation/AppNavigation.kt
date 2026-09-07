package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.home.HomeScreen
import com.example.newsapp.ui.onboarding.OnboardingScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding"){
        composable("onboarding"){
            OnboardingScreen  (
                onFinished = {
                    navController.navigate("home"){
                        popUpTo("onboarding") {
                            inclusive
                        }
                    }
                }
            )
        }
        composable("home"){
            HomeScreen()
        }
    }
}

