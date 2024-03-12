package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.CentersBannersItem
import com.example.digikala.viweModels.HomeViewModel

@Composable
fun CentersBannersSection(
    bannerNumber: Int,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var centersBannerList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    val bannersResult by viewModel.banners.collectAsState()
    when (bannersResult) {
        is NetworkResult.Success -> {
            centersBannerList = bannersResult.data ?: emptyList()
            loading = false

        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", " centersBanners Error :${bannersResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (centersBannerList.isNotEmpty()) {
        CentersBannersItem(imageURl = centersBannerList[bannerNumber].image)
    }
}