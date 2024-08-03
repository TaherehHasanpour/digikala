package com.example.digikala.ui.screens.product_detile

import android.app.LocaleConfig
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper
import com.example.digikala.viweModels.ProductDetailViewModel
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.ui.components.Loading3D
import com.example.digikala.ui.components.OurLoading
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.Oranges
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText

@Composable
fun AllProductCommentScreen(
    navController: NavController,
    productId: String,
    commentCount: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.getCommentList(productId)

    }
    val commentList = viewModel.commentsList.collectAsLazyPagingItems()
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)
            )
            Text(
                text = "${DigitHelper.digitByLocate(commentCount)} ${stringResource(id = R.string.comment)}",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold, color = MaterialTheme.colors.darkText
            )
            Spacer(
                modifier = Modifier
                    .height(3.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.searchBarBg)
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                //paging3
                items(count = commentList.itemCount,
                    key = commentList.itemKey { it._id },
                    contentType = commentList.itemContentType { "comment" }
                ) {
                    CommentItem(commentList[it]!!)
                }

                //paging1
//                items(commentList) {
//                        CommentItem(it!!)
//                }
                commentList.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                val config = LocalConfiguration.current
                                OurLoading(height = config.screenHeightDp.dp, isDark = true)
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(20.dp),

                                    ) {
                                    Loading3D(isDark = true)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}


@Composable
private fun CommentItem(comment: Comment) {
    val dateParts = comment.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()

    val context = LocalContext.current


    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)
    var iconRotation = 0f
    when (comment.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Oranges
            textSuggestion = context.getString(R.string.bad_comment)
            iconRotation = 180f
        }

        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.semiDarkText
            textSuggestion = context.getString(R.string.so_so_comment)
            iconRotation = 0f
        }

        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Green
            textSuggestion = context.getString(R.string.good_comment)
            iconRotation = 0f
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(localSpacing.current.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .width(MaterialTheme.spacing.large)
                    .background(colorSuggestion),
                text = DigitHelper.digitByLocate(comment.star.toString() + ".0"),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                text = DigitHelper.digitByLocate(DigitHelper.gregorianToJalali(year, month, day)),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
            )
            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                Modifier
                    .size(20.dp)
                    .padding(horizontal = MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.grayAlpha
            )
            Text(
                text = comment.userName,
                color = MaterialTheme.colors.grayAlpha,
                style = MaterialTheme.typography.h6
            )
        }

        Divider(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )


        Row(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.large
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconSuggestion),
                contentDescription = "",
                Modifier
                    .size(16.dp)
                    .rotate(iconRotation),
                tint = colorSuggestion
            )
            Text(
                text = textSuggestion,
                Modifier
                    .padding(start = MaterialTheme.spacing.extraSmall),
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                color = colorSuggestion
            )
        }


        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large
                ),
            text = comment.title,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large,
                ),
            text = comment.description,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )


    }
}
