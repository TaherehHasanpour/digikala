package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.viweModels.BasketViewModel

@Composable
fun BasketScreen(navController: NavHostController) {
    Basket(navController)
}

@Composable
fun Basket(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
) {
    val currentCartItemsCount = viewModel.currentCartItemsCount.collectAsState(initial = 0)
    val nextCartItemsCount = viewModel.nextCartItemsCount.collectAsState(initial = 0)

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitle =
        listOf(stringResource(id = R.string.cart), stringResource(id = R.string.next_cart_list))

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.padding(horizontal = localSpacing.current.medium),
            contentColor = MaterialTheme.colors.DigikalaRed,
            backgroundColor = Color.White,
            indicator = {
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(it[selectedTabIndex])
                        .height(3.dp)
                        .background(Color.Red)
                )
            }
        ) {
            tabTitle.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold
                            )
                            val cartCounter = if (index == 0) {
                                currentCartItemsCount.value
                            } else {
                                nextCartItemsCount.value
                            }
                            if (cartCounter > 0) {
                                Spacer(modifier = Modifier.width(10.dp))
                                SetBadgeToTab(selectedTabIndex, index, cartCounter)
                            }
                        }
                    },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    selectedContentColor = MaterialTheme.colors.DigikalaRed,
                    unselectedContentColor = Color.Gray
                )
            }
        }
        when (selectedTabIndex) {
            0 -> ShoppingCart(navController = navController)
            1 -> NextShoppingCart(navController = navController)
        }
    }
}