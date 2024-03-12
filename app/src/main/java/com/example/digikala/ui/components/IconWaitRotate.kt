package com.example.digikala.ui.components

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.digikala.util.Constants.ENGLISH_LNG
import com.example.digikala.util.Constants.USER_LANGUAGE


@Composable
fun IconWaitRotate(painter: Painter, modifier: Modifier, tint: Color) {
    if (USER_LANGUAGE == ENGLISH_LNG) {
        Icon(
            painter = painter,
            contentDescription = "",
            modifier = modifier.graphicsLayer(rotationZ = 180f),
            tint = tint
        )
    } else {
        Icon(painter = painter, contentDescription = "", modifier = modifier, tint = tint)
    }
}

@Composable
fun IconWaitRotate(imageVector: ImageVector, modifier: Modifier, tint: Color) {
    if (USER_LANGUAGE == ENGLISH_LNG) {
        Icon(imageVector = imageVector, contentDescription = "", modifier = modifier, tint = tint)
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = modifier.graphicsLayer(rotationZ = 180f),
            tint = tint
        )

    }
}