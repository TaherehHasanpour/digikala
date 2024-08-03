package com.example.digikala.repository

import com.example.digikala.data.db.FavoriteDao
import com.example.digikala.data.model.profile.FevItem
import com.example.digikala.data.remote.BaseApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteListRepository @Inject constructor(
    private val dao: FavoriteDao
) : BaseApiResponse() {

    val allFavoriteItems:Flow<List<FevItem>> =dao.getAllFavoriteItems()

    suspend fun addFavoriteItem(item: FevItem){
        dao.insertFavoriteItem(item)
    }
    suspend fun removeFavoriteItem(item: FevItem){
        dao.removeFavoriteItem(item)
    }
    suspend fun clearFavoriteList(){
        dao.clearFavoriteList()
    }
    fun  isFavoriteItemExist (id :String): Flow <Boolean> = dao.isFavoriteItemExist(id)
}