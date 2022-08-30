package com.onesmartstar.heinhtetaung.q4b.navigation

import com.onesmartstar.heinhtetaung.q4b.util.Constants.ROUTE_HOME
import com.onesmartstar.heinhtetaung.q4b.util.Constants.ROUTE_LOGIN
import com.onesmartstar.heinhtetaung.q4b.util.Constants.ROUTE_SPLASH

sealed class Screen(val route: String) {
    object Splash : Screen(ROUTE_SPLASH)
    object Login : Screen(ROUTE_LOGIN)
    object Home : Screen(ROUTE_HOME)
}