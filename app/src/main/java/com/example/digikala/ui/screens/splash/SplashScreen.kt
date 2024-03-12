package com.example.digikala.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.components.Loading3D
import com.example.digikala.ui.theme.splashBg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    Splash()
    LaunchedEffect(key1 = true) {
        delay(2500)
        navController.navigate(ScreenPage.Home.route) {
            popUpTo(ScreenPage.Splash.route) {
                inclusive = true
            }
        }
    }
}

@Composable
fun Splash() {

    Box(
        modifier = Modifier
            .background(splashBg)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.digi_logo),
            contentDescription = ""
        )

    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Image(
            modifier = Modifier
                .padding(bottom = 100.dp)
                .height(30.dp),
            painter = painterResource(id = R.drawable.digi_txt_white),
            contentDescription = ""
        )
        Column(
            modifier = Modifier.padding(bottom = 20.dp),
        ) {
            Loading3D(false)
        }
    }

}
