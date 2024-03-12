package com.example.digikala.viweModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.source.ProductCommentsDataSource
import com.example.digikala.repository.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductDetailRepository) :
    ViewModel() {
    val productDetail =
        MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())
    val similarProducts =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val setNewCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    fun getProductById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productDetail.emit(repository.getProductById(id))
        }
    }

    fun getSimilarProducts(categoryId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            similarProducts.emit(repository.getSimilarProducts(categoryId))
        }
    }

    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch(Dispatchers.IO) {
            setNewCommentResult.emit(repository.setNewComment(newComment))
        }
    }

    var commentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty())}
    fun getCommentList(productId: String) {

        commentsList= Pager(
            PagingConfig(10)
        ) {
            ProductCommentsDataSource(repository, productId)
        }.flow.cachedIn(viewModelScope)
    }
}