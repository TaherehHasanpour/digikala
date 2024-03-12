package com.example.digikala.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.util.Constants.USER_ID
import com.example.digikala.util.Constants.USER_LANGUAGE
import com.example.digikala.util.Constants.USER_PASSWORD
import com.example.digikala.util.Constants.USER_PHONE
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viweModels.DataStoreViewModel
import com.example.digikala.viweModels.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    getDataStoreVariables(dataStore)

    if (USER_PHONE != "null") {
        profileViewModel.refreshToken(USER_PHONE, USER_PASSWORD)
        LaunchedEffect(Dispatchers.Main) {
            profileViewModel.loginResponse.collectLatest { loginResponse ->
                when (loginResponse) {
                    is NetworkResult.Success -> {
                        loginResponse.data?.let { user ->
                            if (user.token.isNotEmpty()) {
                                Log.e("3636", "   App config ${user.phone}")
                                dataStore.saveUserToken(user.token)
                                dataStore.saveUserId(user.id)
                                dataStore.saveUserPhoneNumber(user.phone)
                                dataStore.saveUserPassword(USER_PASSWORD)
                                getDataStoreVariables(dataStore)
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }


}

private fun getDataStoreVariables(dataStore: DataStoreViewModel) {
    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)
    USER_PHONE = dataStore.getUserPhoneNumber().toString()
    USER_PASSWORD = dataStore.getUserPassword().toString()
    USER_TOKEN = dataStore.getUserToken().toString()
    USER_ID = dataStore.getUserId().toString()
}