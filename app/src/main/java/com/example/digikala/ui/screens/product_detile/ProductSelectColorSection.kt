package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.spacing
import com.example.digikala.R

@Composable
fun ProductSelectColorSection(
    colors: List<ProductColor>
) {
    Column(
        modifier = Modifier.padding(localSpacing.current.small)
    ) {
        Text(
            text = stringResource(id = R.string.select_color),
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        )
        LazyRow {
            items(colors) {
                ColorChipItem(it)
            }
        }
    }
}