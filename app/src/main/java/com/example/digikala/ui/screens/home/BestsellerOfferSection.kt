package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.util.DigitHelper
import com.example.digikala.viweModels.HomeViewModel

@Composable
fun BestsellerOfferSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var bestsellerOfferList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    val bestsellerProductsResult by viewModel.bestsellerProducts.collectAsState()
    when (bestsellerProductsResult) {
        is NetworkResult.Success -> {
            bestsellerOfferList = bestsellerProductsResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", " bestsellerProducts Error :${bestsellerProductsResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(localSpacing.current.small)
    ) {
        Text(
            text = stringResource(id = R.string.best_selling_products),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h3
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .padding(top = localSpacing.current.medium)
                .height(250.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            itemsIndexed(bestsellerOfferList) {index, item ->
                ProductsHorizontalCard(
                    name = item.name,
                    imageURL = item.image,
                    id = DigitHelper.digitByLocate((index+1).toString())
                )
            }
        }
    }
}


