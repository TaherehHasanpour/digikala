package com.example.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.data.model.checkout.OrderDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.components.OurLoading
import com.example.digikala.ui.screens.basket.ByProcessContinue
import com.example.digikala.ui.screens.basket.CartPriceDetailSection
import com.example.digikala.util.Constants.USER_PHONE
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viweModels.BasketViewModel
import com.example.digikala.viweModels.CheckoutViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    viewModelBasket: BasketViewModel = hiltViewModel(),
    viewModelCheckout: CheckoutViewModel = hiltViewModel()
) {
    val cartDetails by viewModelBasket.cartDetails.collectAsState()
    val ourCartItems by viewModelBasket.ourCartItems.collectAsState()
    var shippingCost by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf("") }
    var nameUser by remember { mutableStateOf("") }
    var phoneUser by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        if (address.isNotBlank()) {
            viewModelCheckout.getShippingCost(address)
        } else {
            viewModelCheckout.getShippingCost("")
        }
    }


    val shippingCostResult by viewModelCheckout.shippingCost.collectAsState()
    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CheckoutScreen Error :${shippingCostResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }
    var orderId by remember { mutableStateOf("") }
    LaunchedEffect(Dispatchers.Main) {
        viewModelCheckout.orderResponse.collectLatest { orderResponseResult ->
            when (orderResponseResult) {
                is NetworkResult.Success -> {
                    orderId = orderResponseResult.data ?: ""
                    navController.navigate(
                        ScreenPage.ConfirmPurchase.withArgs(
                            orderId,
                            cartDetails.payablePrice + shippingCost
                        )
                    )
                }

                is NetworkResult.Error -> {
                    //loading = false
                    Log.e(
                        "3636",
                        "CheckoutScreen orderResponseResult Error :${orderResponseResult.message}"
                    )
                }

                is NetworkResult.Loading -> {
                    // loading = true
                }
            }
        }
    }
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            it != ModalBottomSheetValue.HalfExpanded
        },
        skipHalfExpanded = false
    )
    val coroutineScope = rememberCoroutineScope()


    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetGesturesEnabled = true,
        sheetContent = {
            DeliveryTimeBottomSheet()
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn {
                item { CheckoutTopBarSection(navController = navController) }
                item {
                    CartAddressSection(navController = navController) {
                        if (it.isNotEmpty()) {
                            address = it[0].address
                            nameUser = it[0].name
                            phoneUser = it[0].phone
                        }
                    }
                }
                item {
                    CartItemReviewSection(cartDetails, ourCartItems) {
                        coroutineScope.launch {
                            if (modalBottomSheetState.isVisible) {
                                modalBottomSheetState.hide()
                            } else {
                                modalBottomSheetState.show()
                            }
                        }

                    }
                }
                item { CardInfoSection() }
                item { CartPriceDetailSection(cartDetails, shippingCost) }
            }
            if (loading) {
                OurLoading(height = 65.dp, isDark = true)
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    ByProcessContinue(cartDetails.payablePrice, shippingCost) {
                        viewModelCheckout.setNewOrder(
                            OrderDetail(
                                token = USER_TOKEN,
                                orderTotalPrice = cartDetails.payablePrice + shippingCost,
                                orderTotalDiscount = cartDetails.totalDisCount,
                                orderAddress = address,
                                orderUserName = nameUser,
                                orderUserPhone = phoneUser,
                                orderProducts = ourCartItems
                            )
                        )
                    }
                }
            }
        }
    }

}