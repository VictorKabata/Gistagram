package com.vickikbt.gistagram.ui.screens.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.utils.UiState
import kotlinx.coroutines.launch

class AuthViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _accessToken =
        MutableLiveData<UiState<AccessToken?>>()
    val accessToken: LiveData<UiState<AccessToken?>> get() = _accessToken

    fun fetchAccessToken(code: String) {
        _accessToken.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val response = authRepository.fetchAccessToken(code = code)
                Log.e("TAG", "Fetched access token: $response")
                Log.e("TAG", "Code: $code")
                _accessToken.value = UiState.Success(data = response)
            } catch (e: Exception) {
                _accessToken.value = UiState.Error(error = e.localizedMessage)
            }
        }
    }

}
