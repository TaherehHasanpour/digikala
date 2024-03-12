package com.example.digikala.repository

import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun slider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }

    suspend fun getAmazingProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getAmazingProducts()
        }

    suspend fun getSuperMarketAmazingProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getSuperMarketAmazingProducts()
        }

    suspend fun getCenterBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getCenterBanners()
        }

    suspend fun getCategories(): NetworkResult<List<MainCategory>> =
        safeApiCall {
            api.getCategories()
        }

    suspend fun get4Banners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.get4Banners()
        }

    suspend fun getBestsellerProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getBestsellerProducts()
        }
    suspend fun getMostVisitedProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getMostVisitedProducts()
        }
    suspend fun getMostFavoriteProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getMostFavoriteProducts()
        }
    suspend fun getMostDiscountedProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getMostDiscountedProducts()
        }
}