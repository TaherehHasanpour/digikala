package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localSpacing

@Composable
fun CommentShowMoreItem(
    navController: NavController,
    productId: String,
    commentCount: String
) {
    Column(
        modifier = Modifier
            .padding(vertical = localSpacing.current.medium)
            .width(180.dp)
            .height(200.dp)
            .clickable {
                navController.navigate(ScreenPage.AllProductComment.withArgs(productId, commentCount))
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.show_more), contentDescription = "",
            modifier = Modifier.size(40.dp),
            tint = MaterialTheme.colors.DarkCyan
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localSpacing.current.small),
            text = stringResource(id = R.string.see_all),
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}