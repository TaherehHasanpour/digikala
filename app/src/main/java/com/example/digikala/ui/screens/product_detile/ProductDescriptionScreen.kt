package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText

@Composable
fun ProductDescriptionScreen(
    navController: NavController,
    description: String
) {
    Column(

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localSpacing.current.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                modifier = Modifier
//                .size(24.dp)
                    .padding(horizontal = localSpacing.current.medium)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Text(
                text = stringResource(id = R.string.review),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colors.searchBarBg)
        )
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.product_introduce),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(localSpacing.current.semiMedium),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = description, modifier = Modifier
                    .fillMaxWidth()
                    .padding(localSpacing.current.semiMedium),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText
            )
        }
    }


}