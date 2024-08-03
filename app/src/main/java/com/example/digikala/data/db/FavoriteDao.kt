package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.profile.FevItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(fevItem: FevItem)

    @Query("SELECT * FROM favorite_list_table")
    fun getAllFavoriteItems(): Flow<List<FevItem>>

    @Delete
    suspend fun removeFavoriteItem(item: FevItem)
    @Query("DELETE FROM favorite_list_table")
    suspend fun clearFavoriteList()


    @Query("SELECT EXISTS(SELECT * FROM favorite_list_table WHERE id=:itemId)")
    fun isFavoriteItemExist(itemId: String): Flow < Boolean >
}