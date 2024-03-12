package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.ui.components.manyLogoByLang
import com.example.digikala.ui.theme.DigikalaDarkRed
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.bottombar
import com.example.digikala.ui.theme.localElevation
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper
import com.example.digikala.util.DigitHelper.applyDiscount

@Composable
fun ProductDetailBottomBar(
    navController: NavController,
    item: ProductDetail
) {
    var price = 0L
    item.price?.let {
        price = it
    }
    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
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
            Button(
                onClick = { },
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
                        text = DigitHelper.digitByLocateAndSeparator(applyDiscount(price , discountPercent).toString()),
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