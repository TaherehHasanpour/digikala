package com.example.digikala.viweModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.HomeRepository
import com.example.digikala.repository.ProfileRepository
import com.example.digikala.ui.screens.profile.ProfileScreenStat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {

    var screenStat by mutableStateOf(ProfileScreenStat.LOGIN_STAT)
    var inputPhoneStat by mutableStateOf("")
    var inputPasswordStat by mutableStateOf("")
    var loadingStat by mutableStateOf(false)
    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingStat =true
            loginResponse.emit(
                repository.login(
                    LoginRequest(
                        inputPhoneStat,
                        inputPasswordStat
                    )
                )

            )
        }
    }
    fun refreshToken(phone:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            loginResponse.emit(
                repository.login(
                    LoginRequest(
                        phone,
                        password
                    )
                )

            )
        }

    }
}