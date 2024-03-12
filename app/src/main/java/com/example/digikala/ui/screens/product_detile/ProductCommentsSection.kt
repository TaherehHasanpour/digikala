package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.navigation.ScreenPage
import com.example.digikala.ui.theme.LightCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.util.DigitHelper

@Composable
fun ProductCommentsSection(
    navController: NavController,
    comments: List<Comment>,
    commentCount: Int,
    productId:String
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(localSpacing.current.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = localSpacing.current.semiLarge,
                    horizontal = localSpacing.current.medium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.user_comments),
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(
                        ScreenPage.AllProductComment.withArgs(
                            comments[0].productId,
                            commentCount.toString()
                        )
                    )
                },
                text = "${DigitHelper.digitByLocate(commentCount.toString())} ${stringResource(id = R.string.comment)}",
                color = MaterialTheme.colors.LightCyan,
                style = MaterialTheme.typography.h4
            )
        }
        LazyRow(
            modifier = Modifier.padding(localSpacing.current.small)
        ) {
            items(comments) {
                TextCommentCard(comment = it)
            }
            item {
                CommentShowMoreItem(navController, productId, commentCount.toString())
            }
        }
    }
}