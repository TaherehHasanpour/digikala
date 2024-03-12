package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.spacing

@Composable
fun ColorChipItem(
    item: ProductColor
) {
    Surface(
        shape = localShape.current.biggerMedium,
        elevation = 1.dp,
        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Canvas(modifier = Modifier
                .size(20.dp)
                .border(1.dp, Color.Gray, CircleShape), onDraw = {
                drawCircle(Color(("ff" + item.code.removePrefix("#").lowercase()).toLong(16)))
            })
            Spacer(modifier = Modifier.width(localSpacing.current.small))
            Text(
                text = item.color,
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.h6,
            )
        }
    }
}