package com.onesmartstar.heinhtetaung.q4b.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.onesmartstar.heinhtetaung.q4b.presentation.screens.home.HomeScreen
import com.onesmartstar.heinhtetaung.q4b.presentation.screens.login.LoginScreen
import com.onesmartstar.heinhtetaung.q4b.presentation.screens.splash.SplashScreen

@ExperimentalPermissionsApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    permissions: List<String>,
    activity: ComponentActivity
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(permissions = permissions, activity = activity)
        }
    }
}