package com.example.digikala.ui.screens.product_detile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.digikala.data.model.product_detail.ImageProduct
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopSliderProduct(
    list: List<ImageProduct>
) {
    var imageURL by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = localSpacing.current.extraSmall,
                    vertical = localSpacing.current.small
                )
        ) {
            val pagerState = rememberPagerState()
            Box{
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth(),
                    count = list.size,
                    state = pagerState,
                    contentPadding = PaddingValues(localSpacing.current.medium)
                ) {
                    imageURL = list[it].image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val painter =
                            rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(imageURL)
                                    .apply(
                                        block = fun ImageRequest.Builder.() {
                                            scale(Scale.FILL)
                                        }
                                    ).build())
                        Image(
                            modifier = Modifier
                                .padding(localSpacing.current.extraSmall)
                                .clip(localShape.current.medium)
                                .fillMaxSize(),
                            painter = painter,
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )

                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(localSpacing.current.semiLarge),
                    activeColor = Color.Black,
                    inactiveColor = Color.LightGray,
                    indicatorWidth = localSpacing.current.small,
                    indicatorHeight = localSpacing.current.small,
                    indicatorShape = CircleShape
                )
            }


            LaunchedEffect(key1 = pagerState.currentPage) {
                delay(6000)
                var newPosition = pagerState.currentPage + 1
                if (newPosition > list.size - 1) {
                    newPosition = 0
                }
                pagerState.scrollToPage(newPosition)

            }
        }
    }
}