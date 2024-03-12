package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localElevation
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing


@Composable
fun LoginOrRegisterSection(
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(localSpacing.current.medium)
            .clickable { navController.navigate(ScreenPage.Profile.route) },
        shape = localShape.current.small,
        elevation = localElevation.current.extraSmall
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localSpacing.current.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.import_96_orenge),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .weight(.1f)
                    .align(Alignment.Top)
            )
            Spacer(modifier = Modifier.weight(.5f))
            Column(
                modifier = Modifier.weight(.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(id = R.string.login_or_register),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = R.string.login_or_register_msg),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.weight(.5f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .weight(.1f)
                    .align(Alignment.Top)
                    .padding(top = localSpacing.current.small)
            )
        }
    }
}