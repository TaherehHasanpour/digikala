package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider

import retrofit2.http.GET

interface HomeApiInterface {

    @GET("v1/getSlider")
    suspend fun getSlider(): retrofit2.Response<ResponseResult<List<Slider>>>

    @GET("v1/getAmazingProducts")
    suspend fun getAmazingProducts(): retrofit2.Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getSuperMarketAmazingProducts")
    suspend fun getSuperMarketAmazingProducts(): retrofit2.Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getCenterBanners")
    suspend fun getCenterBanners(): retrofit2.Response<ResponseResult<List<Slider>>>

    @GET("v1/getCategories")
    suspend fun getCategories(): retrofit2.Response<ResponseResult<List<MainCategory>>>

    @GET("v1/get4Banners")
    suspend fun get4Banners(): retrofit2.Response<ResponseResult<List<Slider>>>

    @GET("v1/getBestsellerProducts")
    suspend fun getBestsellerProducts(): retrofit2.Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getMostVisitedProducts")
    suspend fun getMostVisitedProducts(): retrofit2.Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getMostFavoriteProducts")
    suspend fun getMostFavoriteProducts(): retrofit2.Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getMostDiscountedProducts")
    suspend fun getMostDiscountedProducts(): retrofit2.Response<ResponseResult<List<AmazingItem>>>
}