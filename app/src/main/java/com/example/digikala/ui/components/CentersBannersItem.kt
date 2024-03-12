package com.example.digikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.ui.theme.RoundedShep
import com.example.digikala.ui.theme.localSpacing

@Composable
fun CentersBannersItem(imageURl: String) {
    Card(
        shape = MaterialTheme.RoundedShep.semiMedium,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(localSpacing.current.medium)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageURl), contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
fun CentersBannersItem(painter: Painter) {
    Card(
        shape = MaterialTheme.RoundedShep.semiMedium,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(localSpacing.current.medium)
    ) {
        Image(
            painter =  painter , contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }
}