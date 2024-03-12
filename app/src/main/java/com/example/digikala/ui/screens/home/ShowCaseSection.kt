package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.components.RoundedIconBox
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.util.Constants.DIGIJET_URL
import com.example.digikala.util.Constants.DIGIPEY_URL
import com.example.digikala.util.Constants.DIGIPLUS_URL
import com.example.digikala.util.Constants.DIGISTYLE_URL
import com.example.digikala.util.Constants.GIFT_CARE
import com.example.digikala.util.Constants.PINDO

@Composable
fun ShowCaseSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = localSpacing.current.semiMedium,
                vertical = localSpacing.current.biggerSmall
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                title = stringResource(id = R.string.digikala_jet),
                image = painterResource(id = R.drawable.digijet),
                onClick =
                onBoxClick(
                    navController = navController,
                    url = DIGIJET_URL
                )
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digikala_style),
                image = painterResource(id = R.drawable.auction),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGISTYLE_URL
                )
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digikala_pay),
                image = painterResource(id = R.drawable.digipay),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIPEY_URL
                )
            )
            RoundedIconBox(
                bgColor = MaterialTheme.colors.amber,
                title = stringResource(id = R.string.pindo),
                image = painterResource(id = R.drawable.pindo),
                onClick = onBoxClick(
                    navController = navController,
                    url = PINDO
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                title = stringResource(id = R.string.shoping),
                image = painterResource(id = R.drawable.shopping),
                onClick = {}
            )
            RoundedIconBox(
                title = stringResource(id = R.string.dift_care),
                image = painterResource(id = R.drawable.giftcard),
                onClick = onBoxClick(
                    navController = navController,
                    url = GIFT_CARE
                )
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digikala_plus),
                image = painterResource(id = R.drawable.digi_plus_icon),
                onClick =
                onBoxClick(
                    navController = navController,
                    url = DIGIPLUS_URL
                )


            )
            RoundedIconBox(
                bgColor = MaterialTheme.colors.grayCategory,
                title = stringResource(id = R.string.more),
                image = painterResource(id = R.drawable.more),
                onClick = {
                    Log.e("3636", "helooo")
                }
            )
        }
    }
}

@Composable
fun onBoxClick(navController: NavController, url: String): () -> Unit =
    { navController.navigate(route = ScreenPage.WebView.route + "?url=${url}") }

