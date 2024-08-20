package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.R
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.viweModels.HomeViewModel

@Composable
fun MostFavoriteSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var mostFavoriteList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    val mostFavoriteResult by viewModel.mostFavoriteProducts.collectAsState()
    when (mostFavoriteResult) {
        is NetworkResult.Success -> {
            mostFavoriteList = mostFavoriteResult.data ?: emptyList()
            loading = false

        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", " mostFavoriteResult Error :${mostFavoriteResult.message}")
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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
        ) {
            Text(
                text = stringResource(id = R.string.Favorite_Products),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = stringResource(id = R.string.see_all),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.DarkCyan
            )
        }
        LazyRow {
            items(mostFavoriteList) {
                MostFavoriteProductOffer(navController,it)
            }
            item {
                MostFavoriteProductShowMore()
            }
        }
    }
}