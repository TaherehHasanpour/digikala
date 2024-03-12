package com.example.digikala.ui.screens.checkout

import android.app.Activity
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.digikala.util.ZarinpalPurchase

@Composable
fun ConfirmPurchaseScreen(
    navController: NavController,
    orderId: String,
    orderPrice: String
) {
    val context = LocalContext.current
    val activity = context as Activity


    LaunchedEffect(true ){
        ZarinpalPurchase.purchase(
            activity,
            1000,//orderPrice.toLong(),
            "خرید تستی از دیجی کالا"
        ) { transactionID ->
            
            Log.e("3636", "Transaction ID : $transactionID")
        }
    }

    Text(text =orderId )
    Text(text = orderPrice)
}