package com.onesmartstar.heinhtetaung.q4b.activity

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.onesmartstar.heinhtetaung.q4b.navigation.SetupNavGraph
import com.onesmartstar.heinhtetaung.q4b.ui.theme.PrimaryDarkColor
import com.onesmartstar.heinhtetaung.q4b.ui.theme.Q4BTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val permissions = listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Q4BTheme {
                rememberSystemUiController().setStatusBarColor(
                    PrimaryDarkColor,
                    darkIcons = true
                )
                navController = rememberNavController()
                SetupNavGraph(navController = navController, permissions, activity = this)
            }
        }
    }
}
