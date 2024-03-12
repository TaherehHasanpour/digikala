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
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.settingArrow
import com.example.digikala.util.Constants.USER_TOKEN

@Composable
fun ProductSetCommentsSection(
    navController: NavController,
    item: ProductDetail
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = localSpacing.current.medium,
                horizontal = localSpacing.current.semiLarge,
            )
            .clickable {
                if (USER_TOKEN == "null"){
                    navController.navigate(ScreenPage.Profile.route)
                }else{
                    navController.navigate(
                        ScreenPage.NewComment.route + "?productId=${item._id.toString()}?productName=${item.name.toString()}?imageUrl=${
                            item.imageSlider
                                ?.get(
                                    0
                                )
                                .toString()
                        }"
                    )
                }

            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = stringResource(id = R.string.write_your_comment),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Icon(
                Icons.Outlined.KeyboardArrowLeft, contentDescription = "",
                modifier = Modifier
                    .padding(start = 20.dp),
                tint = MaterialTheme.colors.settingArrow
            )
        }
        Text(
            text = stringResource(id = R.string.comment_desc),
            modifier = Modifier
                .padding(start = 40.dp),
            color = MaterialTheme.colors.semiDarkText,
            style = MaterialTheme.typography.h6,
        )
        Spacer(
            modifier = Modifier
                .padding(
                    start = localSpacing.current.large,
                    top = localSpacing.current.medium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )
    }
}