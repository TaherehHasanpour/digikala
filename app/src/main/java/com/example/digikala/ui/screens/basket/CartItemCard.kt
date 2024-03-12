package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.ui.components.manyLogoByLang
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.DigikalaLightGreen
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.veryExtraSmall
import com.example.digikala.util.DigitHelper.digitByLocateAndSeparator
import com.example.digikala.viweModels.BasketViewModel

@Composable
fun CartItemCard(
    item: CartItem,
    mode: CartStatus,
    viewModel: BasketViewModel = hiltViewModel()
) {
    val count = remember {
        mutableIntStateOf(item.count)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = localSpacing.current.extraSmall)
    ) {
        Column(
            modifier = Modifier
                .padding(localSpacing.current.medium)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.your_shopping_list),
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.darkText
                    )
                    Text(
                        text = "${digitByLocateAndSeparator(count.intValue.toString())}  ${
                            stringResource(
                                R.string.goods
                            )
                        }",
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray
                    )

                }
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Options",
                    tint = MaterialTheme.colors.darkText
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.image),
                    contentDescription = "cart item Photo",
                    modifier = Modifier
                        .width(130.dp)
                        .height(90.dp)
                        .weight(.3f)
                )
                Column(
                    modifier = Modifier
                        .weight(.7f)
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.darkText,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall)
                    )

                    DetailRow(
                        painterResource(id = R.drawable.warranty),
                        stringResource(id = R.string.warranty),
                        MaterialTheme.colors.darkText,
                        MaterialTheme.typography.extraSmall,
                    )
                    DetailRow(
                        painterResource(id = R.drawable.store),
                        stringResource(id = R.string.digikala),
                        MaterialTheme.colors.darkText,
                        MaterialTheme.typography.extraSmall,
                    )
                    Row {
                        Column(
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxWidth()
                                .padding(vertical = localSpacing.current.extraSmall),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.in_stock),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(.6f),
                                color = Color.Gray
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(.6f),
                                color = Color.Gray
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )
                        }
                        Column(
                            modifier = Modifier.padding(horizontal = localSpacing.current.small)
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = localSpacing.current.extraSmall),
                                text = item.seller,
                                style = MaterialTheme.typography.extraSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.semiDarkText,

                                )
                            DetailRow(
                                painterResource(id = R.drawable.k1),
                                stringResource(id = R.string.digikala_send),
                                MaterialTheme.colors.DigikalaLightRed,
                                MaterialTheme.typography.veryExtraSmall,
                            )
                            DetailRow(
                                painterResource(id = R.drawable.k2),
                                stringResource(id = R.string.fast_send),
                                MaterialTheme.colors.DigikalaLightGreen,
                                MaterialTheme.typography.veryExtraSmall,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(localSpacing.current.semiLarge))
            Row(
                modifier = Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    modifier = Modifier
                        .clip(localShape.current.semiSmall)
                        .border(1.dp, Color.Gray.copy(.6f), localShape.current.semiSmall)
                ) {
                    if (mode == CartStatus.CURRENT_CART) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = localSpacing.current.small,
                                vertical = localSpacing.current.extraSmall
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_increase_24),
                                contentDescription = "",
                                tint = MaterialTheme.colors.DigikalaRed,
                                modifier = Modifier.clickable {
                                    count.intValue++
                                    viewModel.changeCountItemCart(item.itemId, count.intValue)
                                }
                            )
                            Text(
                                text = digitByLocateAndSeparator(count.intValue.toString()),
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.DigikalaRed,
                                modifier = Modifier.padding(horizontal = localSpacing.current.medium)
                            )
                            if (count.intValue == 1) {
                                Icon(
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.DigikalaRed,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable {
                                            viewModel.removeFromCart(item)
                                        }
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_decrease_24),
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.DigikalaRed,
                                    modifier = Modifier.clickable {
                                        count.intValue--
                                        viewModel.changeCountItemCart(item.itemId, count.intValue)
                                    }
                                )
                            }

                        }
                    } else {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 48.dp,
                                vertical = localSpacing.current.small
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_checkout),
                                contentDescription = "",
                                tint = MaterialTheme.colors.DigikalaRed,
                                modifier = Modifier
                                    .clickable {
                                        viewModel.changeStatusCart(
                                            item.itemId,
                                            CartStatus.CURRENT_CART
                                        )
                                    }
                                    .size(25.dp)
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.padding(localSpacing.current.semiMedium))
                val discountAmount = (item.price * item.discountPercent) / 100
                Column {
                    Text(
                        text = "${digitByLocateAndSeparator(discountAmount.toString())} ${stringResource(
                            id = R.string.discount
                        )}",
                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.DigikalaLightRed,

                        )
                    Row {
                        Text(
                            text = digitByLocateAndSeparator(item.price.toString()),
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,

                            )
                        Icon(
                            painter = manyLogoByLang(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(localSpacing.current.extraSmall)
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(localSpacing.current.semiLarge))
            if (mode == CartStatus.CURRENT_CART) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.changeStatusCart(
                                item.itemId,
                                CartStatus.NEXT_CART
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = stringResource(id = R.string.save_to_next_list),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.DarkCyan,

                        )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colors.DarkCyan,
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.removeFromCart(item)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = stringResource(id = R.string.delete_from_list),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.DigikalaLightRed,

                        )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colors.DigikalaLightRed,
                    )
                }
            }

        }
    }
}


@Composable
fun DetailRow(
    icon: Painter,
    text: String,
    color: Color,
    fontStyle: TextStyle
) {
    Row(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .size(16.dp),
            tint = color,
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = text,
            style = fontStyle,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.semiDarkText,

            )

    }
}
