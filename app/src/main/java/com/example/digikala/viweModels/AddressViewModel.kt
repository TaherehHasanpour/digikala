package com.example.digikala.viweModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.address.UserAddress
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.AddressRepository
import com.example.digikala.util.Constants.USER_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository) :
    ViewModel() {
    val userAddress =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())

    init {
        getUserAddress(USER_TOKEN)
    }

    private fun getUserAddress(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userAddress.emit(repository.getUserAddress(token))
        }
    }

}