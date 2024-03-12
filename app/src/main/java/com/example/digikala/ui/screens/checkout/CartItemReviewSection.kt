package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartDetails
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper

@Composable
fun CartItemReviewSection(
    cartDetails: CartDetails,
    currentCartItem: List<CartItem>,
    onDeliveryTimeClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(id = R.string.deliveryAndTimeMethod),
            modifier = Modifier
                .padding(localSpacing.current.medium),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localSpacing.current.small),
            shape = localShape.current.small,
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(localSpacing.current.semiMedium),
            ) {
                Text(
                    text = stringResource(id = R.string.delivery_1),
                    modifier = Modifier
                        .padding(top = localSpacing.current.medium),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.k1), contentDescription = "",
                        modifier = Modifier
                            .size(16.dp),
                        tint = MaterialTheme.colors.DigikalaLightRed
                    )
                    Spacer(modifier = Modifier.width(localSpacing.current.small))
                    Text(
                        text = stringResource(id = R.string.fast_send),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.extraSmall,
                        color = MaterialTheme.colors.DigikalaLightRed
                    )
                    Spacer(modifier = Modifier.width(localSpacing.current.medium))
                    Text(
                        text = "${DigitHelper.digitByLocate(cartDetails.totalCount.toString())} ${
                            stringResource(
                                id = R.string.goods
                            )
                        }",
                        modifier = Modifier
                            .padding(localSpacing.current.small),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.extraSmall,
                        color = Color.Gray
                    )
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    items(currentCartItem) {
                        CheckoutProductCard(it)
                    }
                }
                Row {
                    Text(
                        text = stringResource(id = R.string.ready_to_send),
                        style = MaterialTheme.typography.extraSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.medium),
                    )
                    Text(
                        text = " :${stringResource(id = R.string.pishtaz_post)} (${stringResource(id = R.string.delivery_delay)})",
                        style = MaterialTheme.typography.extraSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.medium),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MaterialTheme.spacing.medium)
                        .clickable {
                            onDeliveryTimeClick()
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.choose_time),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.DarkCyan,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "",
                        tint = MaterialTheme.colors.DarkCyan,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.small)
                            .size(12.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {

                            }
                    )
                }


            }
        }
    }
}