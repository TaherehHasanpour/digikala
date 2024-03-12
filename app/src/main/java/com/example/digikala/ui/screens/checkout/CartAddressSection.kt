package com.example.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.address.UserAddress
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.OurLoading
import com.example.digikala.ui.theme.LightCyan
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viweModels.AddressViewModel

@Composable
fun CartAddressSection(
    navController: NavController,
    viewModel: AddressViewModel = hiltViewModel(),
    onAddressReady: (List<UserAddress>) -> Unit
) {
    var address = stringResource(id = R.string.no_address)
    var addAddressBtnText = stringResource(id = R.string.add_address)
    var addressName = ""
    var userAddressList by remember {
        mutableStateOf<List<UserAddress>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    val userAddressResult by viewModel.userAddress.collectAsState()
    when (userAddressResult) {
        is NetworkResult.Success -> {
            userAddressList = userAddressResult.data ?: emptyList()
            if (userAddressList.isNotEmpty()) {
                onAddressReady(userAddressList)
                address = userAddressList[0].address
                addAddressBtnText = stringResource(id = R.string.change_address)
                addressName = userAddressList[0].name
            }
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CartAddressSection Error :${userAddressResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }
    if (loading) {
        OurLoading(height = 100.dp, isDark = true)
    } else {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location), contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .weight(.15f)
                    .align(Alignment.CenterVertically),
            )
            Column(
                modifier = Modifier
                    .weight(.85f)
                    .padding(vertical = localSpacing.current.medium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(id = R.string.send_to),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
                Text(
                    text = address,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3
                )
                Text(
                    text = addressName,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = localSpacing.current.medium),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = localSpacing.current.extraSmall)
                    .clickable {

                    },
                text = addAddressBtnText,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.extraSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.LightCyan
            )
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.arrow_back), contentDescription = "",
                tint = MaterialTheme.colors.LightCyan
            )
        }
    }
    Divider(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )
}