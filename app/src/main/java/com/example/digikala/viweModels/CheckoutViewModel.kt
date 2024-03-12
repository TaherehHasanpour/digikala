package com.example.digikala.viweModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.checkout.OrderDetail
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CheckoutRepository
import com.example.digikala.repository.HomeRepository
import com.example.digikala.repository.ProfileRepository
import com.example.digikala.ui.screens.profile.ProfileScreenStat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: CheckoutRepository) :
    ViewModel() {
    val shippingCost = MutableStateFlow<NetworkResult<Int>>(NetworkResult.Loading())
    val orderResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getShippingCost(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            shippingCost.emit(repository.getShippingCost(address))
        }
    }

    fun setNewOrder(cartOrderDetail: OrderDetail) {
        viewModelScope.launch (Dispatchers.IO){
            orderResponse.emit(repository.setNewOrder(cartOrderDetail))
        }
    }

}