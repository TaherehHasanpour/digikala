package com.example.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digikala.navigation.ButtonNavBar
import com.example.digikala.navigation.NavGraph
import com.example.digikala.ui.components.AppConfig
import com.example.digikala.ui.components.ChangeStatusBarColor
import com.example.digikala.ui.theme.DigikalaTheme
import com.example.digikala.util.Constants.ENGLISH_LNG
import com.example.digikala.util.Constants.USER_LANGUAGE
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.util.LocalUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()
                ChangeStatusBarColor(navController = navController)

                AppConfig()
                LocalUtil.setLocale(LocalContext.current, USER_LANGUAGE)
                val direction = if (USER_LANGUAGE == ENGLISH_LNG) {
                    LayoutDirection.Ltr
                } else {
                    LayoutDirection.Rtl
                }
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Scaffold(
                        bottomBar = {
                            ButtonNavBar(navController = navController, onItemClick = {
                                navController.navigate(it.route)
                            })
                        }
                    ) {
                            NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
