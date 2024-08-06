package com.example.digikala.viweModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.address.UserAddress
import com.example.digikala.data.model.profile.FevItem
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.AddressRepository
import com.example.digikala.repository.FavoriteListRepository
import com.example.digikala.util.Constants.USER_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(private val repository: FavoriteListRepository) :
    ViewModel() {
    val allFavoriteItems: Flow<List<FevItem>> = repository.allFavoriteItems

    fun addFavoriteItem(item: FevItem) {
        viewModelScope.launch (Dispatchers.IO){
            launch {
              repository.addFavoriteItem(item)
            }
        }
    }
    fun removeFavoriteItem(item: FevItem) {
        viewModelScope.launch (Dispatchers.IO){
            launch {
                repository.removeFavoriteItem(item)
            }
        }
    }
     fun clearFavoriteList() {
        viewModelScope.launch (Dispatchers.IO){
            launch {
                repository.clearFavoriteList()
            }
        }
    }
    fun isFavoriteItemExist(id:String):Flow<Boolean> {
               return  repository.isFavoriteItemExist(id)

        }


}