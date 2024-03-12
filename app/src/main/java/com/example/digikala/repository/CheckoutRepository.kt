package com.example.digikala.repository

import com.example.digikala.data.model.checkout.OrderDetail
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.CheckoutApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) :
    BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
            api.getShippingCost(address)
        }

    suspend fun setNewOrder(cartOrderDetail: OrderDetail): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(cartOrderDetail)
        }

}