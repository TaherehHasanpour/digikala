package com.example.digikala.ui.screens.product_detile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.OurLoading
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.util.Constants
import com.example.digikala.viweModels.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewCommentScreen(
    navController: NavController,
    productId: String,
    productName: String,
    imageUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Header(navController = navController, productName = productName, imageUrl = imageUrl)
        CommentForm(navController = navController, productId = productId)
    }

}

@Composable
private fun Header(
    navController: NavController,
    productName: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = localSpacing.current.extraSmall,
                horizontal = localSpacing.current.small
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
            )
        }
        val img = rememberAsyncImagePainter(imageUrl)
        Image(
            painter = img,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = localSpacing.current.small)
                .clip(localShape.current.small)
                .size(50.dp)

        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.your_comment),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = productName,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText
            )
        }

    }
    Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)


}


@Composable
private fun CommentForm(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navController: NavController,
    productId: String
) {
    var commentTitle by remember {
        mutableStateOf("")
    }
    var commentBody by remember {
        mutableStateOf("")
    }
    var checkedStat by remember {
        mutableStateOf(false)
    }
    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }
    val context = LocalContext.current
    val score = when (sliderValue.toInt()) {
        1 -> ""
        2 -> stringResource(id = R.string.very_bad)
        3 -> stringResource(id = R.string.bad)
        4 -> stringResource(id = R.string.normal)
        5 -> stringResource(id = R.string.good)
        6 -> stringResource(id = R.string.very_good)
        else -> ""
    }
    var loading by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        viewModel.setNewCommentResult.collectLatest { setNewCommentResult ->
            when (setNewCommentResult) {
                is NetworkResult.Success -> {
                    if (setNewCommentResult.message.equals("کامنت با موفقیت ثبت شد")) { //todo edit backend
                        navController.popBackStack()
                    }
                    loading = false

                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "setNewCommentResult Error :${setNewCommentResult.message}")
                }

                is NetworkResult.Loading -> {
                }
            }
        }
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = localSpacing.current.medium),
        text = score,
        color = MaterialTheme.colors.darkText,
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Center
    )
    Slider(
        modifier = Modifier
            .padding(horizontal = localSpacing.current.large),
        value = sliderValue, onValueChange = {
            sliderValue = it
        },
        onValueChangeFinished = {

        },
        valueRange = 1f..6f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.amber,
            activeTrackColor = MaterialTheme.colors.amber,
            inactiveTrackColor = MaterialTheme.colors.grayCategory,
            activeTickColor = MaterialTheme.colors.amber,
            inactiveTickColor = MaterialTheme.colors.grayAlpha,
        )
    )
    Divider(
        modifier = Modifier
            .padding(top = localSpacing.current.semiMedium),
        color = MaterialTheme.colors.grayCategory,
        thickness = 1.dp
    )

    Column(
        modifier = Modifier.padding(horizontal = localSpacing.current.medium)
    ) {
        Text(
            text = stringResource(id = R.string.say_your_comment),
            modifier = Modifier.padding(vertical = localSpacing.current.medium),
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.comment_title),
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h5,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = commentTitle,
            onValueChange = {
                commentTitle = it
            },
            maxLines = 1,
            singleLine = true,
            shape = localShape.current.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
        Text(
            modifier = Modifier.padding(top = localSpacing.current.biggerMedium),
            text = stringResource(id = R.string.comment_text),
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h5,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = commentBody,
            onValueChange = {
                commentBody = it
            },
            maxLines = 3,
            singleLine = true,
            shape = localShape.current.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = localSpacing.current.small,
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = checkedStat, onCheckedChange = {
                    checkedStat = it
                },
                colors = CheckboxDefaults.colors(
                    MaterialTheme.colors.DarkCyan
                )
            )
            Text(
                text = stringResource(id = R.string.comment_anonymously),
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.h5,
            )
        }
        Divider(
            color = MaterialTheme.colors.grayCategory,
            thickness = 2.dp
        )
        if (loading) {
            OurLoading(height = 60.dp, isDark = true)
        } else {
            OutlinedButton(
                modifier = Modifier.padding(
                    vertical = localSpacing.current.medium
                ),
                onClick = {
                    loading = true
                    val newComment = NewComment(
                        token = Constants.USER_TOKEN,
                        productId = productId,
                        star = (sliderValue - 1).toInt(),
                        title = commentTitle,
                        description = commentBody,
                        userName = "کاربر مهمان" //todo change user name
                    )

                    if (newComment.title.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_title_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.star == 0) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_star_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.description.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_body_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.setNewComment(newComment)
                    }

                },
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = localSpacing.current.extraSmall)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.set_new_comment),
                    color = MaterialTheme.colors.semiDarkText,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}