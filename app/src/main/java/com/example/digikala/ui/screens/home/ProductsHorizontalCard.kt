package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraBoldNumber
import com.example.digikala.ui.theme.localSpacing

@Composable
fun ProductsHorizontalCard(
    id: String,
    imageURL: String,
    name: String
) {
    Row(
        modifier = Modifier
            .width(320.dp)
            .padding(bottom = localSpacing.current.extraSmall),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageURL), contentDescription = "",
            modifier = Modifier
                .weight(.3f)
                .fillMaxHeight()
        )
        Text(
            text = id,
            modifier = Modifier
                .weight(.1f)
                .padding(localSpacing.current.small),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colors.DarkCyan,
            style = MaterialTheme.typography.extraBoldNumber
        )
        Column (
            modifier = Modifier
                .weight(.6f)
                .fillMaxHeight()
                .padding(localSpacing.current.small)
        ){
            Text(
                text = name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(localSpacing.current.small)
            )
        }
    }
}