package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.ui.components.manyLogoByLang
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.DigikalaDarkRed
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.util.Constants
import com.example.digikala.util.DigitHelper

@Composable
fun MostFavoriteProductOffer(
    item: AmazingItem
) {
    val textFromPriceWaitDiscountPercent = if (Constants.USER_LANGUAGE == Constants.ENGLISH_LNG) {
        DigitHelper.digitByLocateAndSeparator(
            (DigitHelper.applyDiscount(
                item.price,
                item.discountPercent
            ) / 300000)
                .toString()
        )
    } else {
        DigitHelper.digitByLocateAndSeparator(
            DigitHelper.applyDiscount(item.price, item.discountPercent)
                .toString()
        )
    }
    val textPrice = if (Constants.USER_LANGUAGE == Constants.ENGLISH_LNG) {
        DigitHelper.digitByLocateAndSeparator(((item.price) / 300000).toString())
    } else {
        DigitHelper.digitByLocateAndSeparator(item.price.toString())
    }
    Column(
        modifier = Modifier
            .width(170.dp)
            .padding(
                vertical = localSpacing.current.semiLarge,
                horizontal = localSpacing.current.semiSmall
            ),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = localSpacing.current.small)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = localSpacing.current.extraSmall)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(item.image), contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = localSpacing.current.small)
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(48.dp)
                            .padding(vertical = localSpacing.current.small),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.darkText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.in_stock),
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(2.dp),
                            tint = MaterialTheme.colors.DarkCyan
                        )
                        Text(
                            text = item.seller,
                            style = MaterialTheme.typography.extraSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.semiDarkText,

                            )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = localSpacing.current.small),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(24.dp)
                                .background(
                                    color = MaterialTheme.colors.DigikalaDarkRed,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${DigitHelper.digitByLocateAndSeparator(item.discountPercent.toString())}%",
                                color = Color.White,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column {
                            Row {
                                Text(
                                    text = textFromPriceWaitDiscountPercent,
                                    style = MaterialTheme.typography.body2,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Icon(
                                    painter = manyLogoByLang(),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(localSpacing.current.semiLarge)
                                        .padding(localSpacing.current.extraSmall)
                                )
                            }
                            Text(
                                text = textPrice,
                                color = Color.LightGray,
                                style = MaterialTheme.typography.body2,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }
                }
            }
            Divider(
                modifier = Modifier
                    .padding(start = localSpacing.current.semiMedium)
                    .width(1.dp)
                    .height(320.dp)
                    .alpha(0.4f),
                color = Color.LightGray
            )
        }

    }
}
