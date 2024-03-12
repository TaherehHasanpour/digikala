package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.util.Constants
import com.example.digikala.util.LocalUtil
import com.example.digikala.viweModels.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController)
}

@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LocalUtil.setLocale(LocalContext.current, Constants.USER_LANGUAGE)
    LaunchedEffect(true) {
        refreshDataFromServer(viewModel)
    }
    SwipeRefreshSection(viewModel, navController)

}

@Composable
private fun SwipeRefreshSection(viewModel: HomeViewModel, navController: NavHostController) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                refreshDataFromServer(viewModel)
                Log.e("3636", "swipeRefresh")
            }
        }) {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            item { SearchBarSection() }
            item { TopSlider() }
            item { ShowCaseSection(navController) }
            item { AmazingOfferSection(navController) }
            item { ProposalCardSection() }
            item { SuperMarketOfferSection(navController) }
            item { CategoryListSection() }
            item { CentersBannersSection(0) }
            item { BestsellerOfferSection() }
            item { CentersBannersSection(1) }
            item { MostFavoriteSection() }
            item { CentersBannersSection(2) }
            item { MostVisitedOfferSection() }
            item { CentersBannersSection(3) }
            item { MostDiscountedSection() }
        }
    }
}


private suspend fun refreshDataFromServer(viewModel: HomeViewModel) {
    viewModel.getDataFormServer()
}
