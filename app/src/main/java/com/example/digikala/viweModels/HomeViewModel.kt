package com.example.digikala.viweModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingProducts =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val superMarketAmazing =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val centerBanners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val category = MutableStateFlow<NetworkResult<List<MainCategory>>>(NetworkResult.Loading())
    val banners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val bestsellerProducts =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostVisitedProducts =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostFavoriteProducts =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostDiscountedProducts =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())

    suspend fun getDataFormServer() {
        viewModelScope.launch (Dispatchers.IO){
            launch {
                slider.emit(repository.slider())
            }
            launch {
                amazingProducts.emit(repository.getAmazingProducts())
            }
            launch {
                superMarketAmazing.emit(repository.getSuperMarketAmazingProducts())
            }
            launch {
                centerBanners.emit(repository.getCenterBanners())
            }
            launch {
                category.emit(repository.getCategories())
            }
            launch {
                banners.emit(repository.get4Banners())
            }
            launch {
                bestsellerProducts.emit(repository.getBestsellerProducts())
            }
            launch {
                mostVisitedProducts.emit(repository.getMostVisitedProducts())
            }
            launch {
                mostFavoriteProducts.emit(repository.getMostFavoriteProducts())
            }
            launch {
                mostDiscountedProducts.emit(repository.getMostDiscountedProducts())
            }

        }
    }

}