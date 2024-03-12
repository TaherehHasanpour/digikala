package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.util.DigitHelper

@Composable
fun SetBadgeToTab(
    selectedTabIndex: Int,
    index: Int,
    cartCounter: Int
) {
    Card(
        modifier = Modifier.background(Color.Transparent)
    ) {
        val color = if (selectedTabIndex == index) {
            MaterialTheme.colors.DigikalaRed
        } else {
            Color.LightGray
        }
        Text(
            modifier =Modifier.background(color)
                .padding(horizontal = localSpacing.current.semiSmall),
            text = DigitHelper.digitByLocateAndSeparator(cartCounter.toString()),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }

}