package com.example.digikala.navigation

import androidx.compose.ui.graphics.painter.Painter

data class ButtonNavigation(
    val name: String,
    val route: String,
    val selectIcon: Painter,
    val unselectIcon: Painter
)
