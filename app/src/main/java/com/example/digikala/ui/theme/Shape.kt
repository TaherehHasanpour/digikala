package com.example.digikala.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class RoundedShep(
    val extraSmall: RoundedCornerShape = RoundedCornerShape(4.dp),
    val semiSmall: RoundedCornerShape = RoundedCornerShape(6.dp),
    val small: RoundedCornerShape = RoundedCornerShape(8.dp),
    val bigSmall: RoundedCornerShape = RoundedCornerShape(10.dp),
    val semiMedium: RoundedCornerShape = RoundedCornerShape(14.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(16.dp),
    val biggerMedium: RoundedCornerShape = RoundedCornerShape(16.dp),
    val large: RoundedCornerShape = RoundedCornerShape(32.dp)
)

val localShape = compositionLocalOf { RoundedShep() }
val MaterialTheme.RoundedShep: RoundedShep
    @Composable
    @ReadOnlyComposable
    get() = localShape.current

//val Shapes = Shapes(
//    small = RoundedCornerShape(4.dp),
//    medium = RoundedCornerShape(4.dp),
//    large = RoundedCornerShape(8.dp),
//)