package com.example.digikala.repository

import com.example.digikala.data.db.CartDao
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.BasketApiInterface
import com.example.digikala.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao
) : BaseApiResponse() {

    val currentCartItems = dao.getAllItem(CartStatus.CURRENT_CART)
    val nextCartItems = dao.getAllItem(CartStatus.NEXT_CART)
    val currentCartItemsCount = dao.getCardItemCount(CartStatus.CURRENT_CART)
    val nextCartItemsCount = dao.getCardItemCount(CartStatus.NEXT_CART)

    suspend fun getSuggestedItems(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun insertItemCart(cart: CartItem) {
        dao.insertItemCart(cart)
    }

    suspend fun removeFromCart(cart: CartItem) {
        dao.removeFromCart(cart)
    }

    suspend fun changeCountItemCart(id: String, newCount: Int) {
        dao.changeCountItemCart(id, newCount)
    }

    suspend fun changeCountItemCart(id: String, newStatus: CartStatus) {
        dao.changeStatusCart(id, newStatus)
    }
    fun isItemExistInBasket(id: String):Flow<Boolean> =
        dao.isItemExistInBasket(id)
    fun getItemCountInBasket(id: String):Flow<Int> =
        dao.getItemCountInBasket(id)


}
