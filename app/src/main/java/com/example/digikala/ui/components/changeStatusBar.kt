package com.example.digikala.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digikala.navigation.ScreenPage
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChangeStatusBarColor(
    navController: NavHostController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()
    val statusBarColor = if (MaterialTheme.colors.isLight){
        Color.White
    }else{
        Color.Black
    }
    when (backStackEntry?.destination?.route) {
        ScreenPage.Splash.route -> {
            SideEffect {
                systemUiController.setStatusBarColor(color = Color.Gray)
            }


        }

        else -> {
            SideEffect {
                systemUiController.setStatusBarColor(statusBarColor)
            }

        }
    }
}