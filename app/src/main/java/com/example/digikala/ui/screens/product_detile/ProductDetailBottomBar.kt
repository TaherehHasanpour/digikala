package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.components.manyLogoByLang
import com.example.digikala.ui.theme.DigikalaDarkRed
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.bottombar
import com.example.digikala.ui.theme.localElevation
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper
import com.example.digikala.util.DigitHelper.applyDiscount
import com.example.digikala.util.DigitHelper.digitByLocateAndSeparator
import com.example.digikala.viweModels.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailBottomBar(
    navController: NavController,
    item: ProductDetail,
    viewModel: BasketViewModel = hiltViewModel()
) {
    var price = 0L
    item.price?.let {
        price = it
    }
    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
    }
    var isShowAddToBasket by remember {
        mutableStateOf(true)
    }
    var isLaunchedEffectCompleted by remember {
        mutableStateOf(false)
    }
    var countItemInBasket by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(true) {
        viewModel.isItemExistInBasket(item._id ?: "").collectLatest {
            isShowAddToBasket = !it
        }
        isLaunchedEffectCompleted = true
    }
    LaunchedEffect(true) {
        viewModel.getItemCountInBasket(item._id ?: "").collectLatest {
            countItemInBasket = it
        }
    }


    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottombar,
        elevation = localElevation.current.small,
        modifier = Modifier
            .height(70.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = localSpacing.current.medium,
                    vertical = localSpacing.current.biggerSmall
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                if (isShowAddToBasket && isLaunchedEffectCompleted) {
                    Button(
                        onClick = {
                            viewModel.insertItemCart(
                                CartItem(
                                    itemId = item._id ?: "",
                                    name = item.name ?: "",
                                    seller = item.seller ?: "",
                                    price = item.price ?: 0,
                                    discountPercent = item.discountPercent ?: 0,
                                    image = item.imageSlider?.get(0)?.image ?: "",
                                    count = 1,
                                    cartStatus = CartStatus.CURRENT_CART
                                )
                            )
                            isShowAddToBasket = false
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.DigikalaRed),
                        shape = localShape.current.small
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_to_basket),
                            style = MaterialTheme.typography.h5,
                            color = Color.White,
                            modifier = Modifier
                                .padding(vertical = localSpacing.current.extraSmall)
                        )
                    }
                } else if (!isShowAddToBasket && isLaunchedEffectCompleted) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.DigikalaLightRed),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = digitByLocateAndSeparator(countItemInBasket.toString()),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall)
                            )
                        }
                        Column {
                            Text(
                                text = stringResource(id = R.string.in_your_basket),
                                color = Color.LightGray,
                                style = MaterialTheme.typography.h5,
                            )
                            Text(
                                text = stringResource(id = R.string.view_in_cart),
                                modifier = Modifier.clickable {
                                    navController.navigate(ScreenPage.Basket.route)
                                },
                                color = MaterialTheme.colors.DigikalaLightRed,
                                style = MaterialTheme.typography.h5,
                            )
                        }
                    }
                }
            }



            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.DigikalaDarkRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically),

                        ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(discountPercent.toString())}%",
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                        )
                    }
                    Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))

                    Text(
                        text = DigitHelper.digitByLocateAndSeparator((price).toString()),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2,
                        textDecoration = TextDecoration.LineThrough,
                    )

                }
                Row {
                    Text(
                        text = DigitHelper.digitByLocateAndSeparator(
                            applyDiscount(
                                price,
                                discountPercent
                            ).toString()
                        ),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Image(
                        painter = manyLogoByLang(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )

                }
            }

        }
    }
}