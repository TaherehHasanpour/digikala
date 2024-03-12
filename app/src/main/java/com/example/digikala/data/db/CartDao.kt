package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItemCart(cartDao: CartItem)

    @Query("SELECT * FROM shopping_cart WHERE cartStatus=:status")
    fun getAllItem(status: CartStatus): Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(item: CartItem)

    @Query("UPDATE shopping_cart SET count=:newCount WHERE itemId=:id")
    suspend fun changeCountItemCart(id: String, newCount: Int)

    @Query("UPDATE shopping_cart SET cartStatus=:newStatus WHERE itemId=:id")
    suspend fun changeStatusCart(id: String, newStatus: CartStatus)

    @Query("SELECT total(count)as count FROM shopping_cart WHERE cartStatus=:status")
    fun getCardItemCount(status: CartStatus): Flow<Int>
}