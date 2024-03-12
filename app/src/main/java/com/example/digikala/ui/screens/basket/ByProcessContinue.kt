package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.RoundedShep
import com.example.digikala.ui.theme.elevation
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.R
import com.example.digikala.ui.components.manyLogoByLang
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper

@Composable
fun ByProcessContinue(
    price: Long,
    shoppingCost: Int = 0,
    onClick: () -> Unit
) {
    var title = stringResource(id = R.string.goods_total_price)
    if (shoppingCost > 0) {
        title = stringResource(R.string.final_price)
    }
    Card(
        shape = MaterialTheme.RoundedShep.medium,
        elevation = MaterialTheme.elevation.extraSmall,
        border = BorderStroke(1.dp, Color.LightGray.copy(.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = localSpacing.current.medium,
                    vertical = localSpacing.current.semiMedium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.DigikalaRed),
                shape = localShape.current.small
            ) {
                Text(
                    text = stringResource(id = R.string.purchase_process),
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(
                        horizontal = localSpacing.current.biggerMedium,
                        vertical = localSpacing.current.extraSmall
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colors.darkText,
                    style = MaterialTheme.typography.h6,
                )
                Row {
                    Text(
                        text = DigitHelper.digitByLocateAndSeparator((price + shoppingCost).toString()),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Icon(
                        painter = manyLogoByLang(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(localSpacing.current.semiLarge)
                            .padding(MaterialTheme.spacing.extraSmall)
                    )
                }
            }
        }
    }
}