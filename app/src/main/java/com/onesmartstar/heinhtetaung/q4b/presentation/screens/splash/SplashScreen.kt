package com.onesmartstar.heinhtetaung.q4b.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.onesmartstar.heinhtetaung.q4b.BuildConfig
import com.onesmartstar.heinhtetaung.q4b.R
import com.onesmartstar.heinhtetaung.q4b.navigation.Screen
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GradientColorEnd
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GradientColorStart
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val checkToken by splashViewModel.checkToken.collectAsState()
    LaunchedEffect(key1 = true) {
        delay(1500L)
        navController.popBackStack()
        if (checkToken.isEmpty()) {
            navController.navigate(Screen.Login.route)
        } else {
            navController.navigate(Screen.Home.route)
        }
    }

    Splash()

}

@Composable
fun Splash() {
    val modifier = Modifier.background(
        Brush.verticalGradient(listOf(GradientColorStart, GradientColorEnd)),
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(width = 300.dp, height = 150.dp),
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Logo"
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(all = 12.dp),
            text = "Version : ${BuildConfig.VERSION_NAME}",
            color = Color.White,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}