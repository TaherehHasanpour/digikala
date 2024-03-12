package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.util.DigitHelper

@Composable
fun IconWithBadge(
    currentCart: Int,
    icon: Painter,

    ) {
    Box(
        modifier = Modifier.height(28.dp),
    ) {
        Box(
            modifier = Modifier.height(28.dp).width(36.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                modifier = Modifier.height(24.dp),
                painter = icon,
                contentDescription = ""
            )
        }
        Box(
            modifier = Modifier.height(28.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                shape = localShape.current.extraSmall,
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    text = DigitHelper.digitByLocateAndSeparator(currentCart.toString()),
                    modifier = Modifier
                        .background(MaterialTheme.colors.DigikalaRed)
                        .height(16.dp)
                        .padding(horizontal = localSpacing.current.semiSmall),
                    style = MaterialTheme.typography.extraSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}