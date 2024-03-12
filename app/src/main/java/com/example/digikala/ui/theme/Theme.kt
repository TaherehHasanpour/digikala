package com.example.digikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    secondary = Teal200,
    primaryVariant = Purple700
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    secondary = Teal200,
    primaryVariant = Purple700
)

@Composable
fun DigikalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content,
        shapes = Shapes()
    )
}