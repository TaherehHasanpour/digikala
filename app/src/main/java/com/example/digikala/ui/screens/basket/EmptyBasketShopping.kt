package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.semiDarkText

@Composable
fun EmptyBasketShopping() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = localSpacing.current.small),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_cart), contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.heightIn(200.dp)
        )

        Text(
            text = stringResource(id = R.string.cart_is_empty),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.semiDarkText
        )
        Spacer(modifier = Modifier.heightIn(localSpacing.current.small))
    }
}