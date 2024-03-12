package com.example.digikala.viweModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.basket.CartDetails
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.BasketRepository
import com.example.digikala.ui.screens.basket.BasketScreenState
import com.example.digikala.util.DigitHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepository) :
    ViewModel() {

    val suggestedList = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val cartDetails = MutableStateFlow(CartDetails(0, 0, 0, 0))


    private val _currentCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val currentCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _currentCartItems

    private val _nextCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val nextCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _nextCartItems

    val currentCartItemsCount = repository.currentCartItemsCount
    val nextCartItemsCount = repository.nextCartItemsCount

    val ourCartItems: MutableStateFlow<List<CartItem>> = MutableStateFlow(emptyList())
    private fun calculateCartDetail(items: List<CartItem>) {
        var totalCount = 0
        var totalPrice = 0L
        var totalDisCount = 0L
        var payablePrice = 0L
        items.forEach {
            totalPrice += it.price * it.count
            payablePrice += DigitHelper.applyDiscount(it.price, it.discountPercent) * it.count
            totalCount += it.count
        }
        totalDisCount = totalPrice - payablePrice
        cartDetails.value = CartDetails(totalCount, totalPrice, totalDisCount, payablePrice)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                repository.currentCartItems.collectLatest { cartItems ->
                    _currentCartItems.emit(BasketScreenState.Success(cartItems))
                    ourCartItems.emit(cartItems)
                }
            }
            launch {
                repository.currentCartItems.collectLatest { cartItems ->
                    calculateCartDetail(cartItems)
                }
            }
            launch {
                repository.nextCartItems.collectLatest { cartItems ->
                    _nextCartItems.emit(BasketScreenState.Success(cartItems))
                }
            }
        }
    }

    fun getSuggestedItems() {
        viewModelScope.launch (Dispatchers.IO){
            suggestedList.emit(repository.getSuggestedItems())
        }
    }

    fun insertItemCart(cart: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertItemCart(cart)
        }
    }

    fun removeFromCart(cart: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(cart)
        }
    }

    fun changeCountItemCart(id: String, newCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCountItemCart(id, newCount)
        }
    }

    fun changeStatusCart(id: String, newStatus: CartStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCountItemCart(id, newStatus)
        }
    }

}

