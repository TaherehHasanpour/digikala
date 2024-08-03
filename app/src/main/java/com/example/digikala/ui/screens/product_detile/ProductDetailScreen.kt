package com.example.digikala.ui.screens.product_detile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.data.model.product_detail.ImageProduct
import com.example.digikala.data.model.product_detail.Price
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.OurLoading
import com.example.digikala.viweModels.ProductDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    var loading by remember { mutableStateOf(false) }
    var productDetail by remember { mutableStateOf(ProductDetail()) }
    var imageSliderList by remember { mutableStateOf<List<ImageProduct>>(emptyList()) }
    var productColorList by remember { mutableStateOf<List<ProductColor>>(emptyList()) }
    var comments by rememberSaveable { mutableStateOf<List<Comment>>(emptyList()) }
    var productPriceList by rememberSaveable { mutableStateOf<List<Price>>(emptyList()) }
    var categoryId by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var technicalFeatures by remember { mutableStateOf("") }
    var commentCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Dispatchers.Main) {
        viewModel.getProductById(productId)
        viewModel.productDetail.collectLatest { productDetailResult ->
            when (productDetailResult) {
                is NetworkResult.Success -> {
                    productDetail = productDetailResult.data!!
                    imageSliderList = productDetailResult.data.imageSlider ?: emptyList()
                    productColorList = productDetailResult.data.colors ?: emptyList()
                    productPriceList = productDetailResult.data.priceList ?: emptyList()
                    categoryId = productDetailResult.data.categoryId ?: ""
                    description = productDetailResult.data.description ?: ""
                    technicalFeatures = productDetailResult.data.technicalFeatures.toString()
                    comments = productDetailResult.data.comments ?: emptyList()
                    commentCount = productDetailResult.data.commentCount ?: 0
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen Error :${productDetailResult.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }
    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        Scaffold(
            bottomBar = {
                ProductDetailBottomBar(navController, productDetail)
            },
            topBar = {
                ProductTopAppBar(navController,productDetail)
            }
        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {
                item { TopSliderProduct(imageSliderList) }
                item { ProductDetailHeaderSection(productDetail) }
                item { ProductSelectColorSection(productColorList) }
                item { SellerInfoSection() }
                item { SimilarProductSection(categoryId) }
                item { ProductDescriptionSection(navController, description, technicalFeatures) }
                item { ProductCommentsSection(navController, comments, commentCount,productId) }
                item { ProductSetCommentsSection(navController, productDetail) }
            }
        }
    }

}