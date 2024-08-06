package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.spacing
import com.example.digikala.R

val selectedColorItem: MutableState<ProductColor?> = mutableStateOf(null)

@Composable
fun ProductSelectColorSection(
    colors: List<ProductColor>,
    onSelectChange: (String) -> Unit = { colorName ->
        colors.forEach {
            if (it.color == colorName) {
                selectedColorItem.value = it
            }
        }
    }
) {
    Column(
        modifier = Modifier.padding(localSpacing.current.small)
    ) {
        Text(
            text = "رنگ : ${ if(selectedColorItem.value?.color==null) "انتخاب نشده" else selectedColorItem.value?.color }  ",
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        )
        var colorStat by remember {
            mutableStateOf<List<ProductColor>>(emptyList())
        }
        colorStat = colors
        LazyRow {
            items(colorStat) {productColor->
                ColorChipItem(
                    item = productColor,
                    isSelected = selectedColorItem.value?.color == productColor.color
                ) {
                    onSelectChange(it)
                }
            }
        }
    }
}


