package com.example.digikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viweModels.BasketViewModel

@Composable
fun ShoppingCart(
    viewModel: BasketViewModel = hiltViewModel(),
    navController: NavController
) {

    val currentCartItemsState: BasketScreenState<List<CartItem>> by viewModel.currentCartItems.collectAsState(
        BasketScreenState.Loading
    )
    val cartDetail = viewModel.cartDetails.collectAsState()
    var isCardEmpty by remember {
        mutableStateOf(true)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 60.dp)
        ) {
            item {
                if (USER_TOKEN == "null") {
                    LoginOrRegisterSection(navController = navController)
                }
            }
            when (currentCartItemsState) {
                is BasketScreenState.Success -> {
                    if ((currentCartItemsState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()) {
                        isCardEmpty = true
                        item { EmptyBasketShopping() }
                        item { SuggestListSection() }
                    } else {
                        isCardEmpty = false
                        items((currentCartItemsState as BasketScreenState.Success<List<CartItem>>).data) {
                            CartItemCard(it, CartStatus.CURRENT_CART)
                        }
                        item {
                            CartPriceDetailSection(cartDetail.value)
                        }
                    }
                }

                is BasketScreenState.Error -> {
                    Log.e("3636", "Error ShoppingCart ")
                }

                is BasketScreenState.Loading -> {

                    item {
                        val config = LocalConfiguration.current
                        Column(
                            modifier = Modifier
                                .height(config.screenHeightDp.dp - 60.dp)
                                .fillMaxWidth()
                                .padding(vertical = localSpacing.current.small),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = stringResource(id = R.string.please_wait),
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.darkText,
                            )
                        }
                    }
                }
            }
        }
        if (!isCardEmpty) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 60.dp)
            ) {
                ByProcessContinue(cartDetail.value.payablePrice) {
                    if (USER_TOKEN == "null") {
                        navController.navigate(ScreenPage.Profile.route)
                    } else {
                        navController.navigate(ScreenPage.Checkout.route)
                    }
                }
            }
        }

    }

}