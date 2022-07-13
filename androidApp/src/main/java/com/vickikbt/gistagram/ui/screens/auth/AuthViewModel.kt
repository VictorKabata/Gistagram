package com.vickikbt.gistagram.ui.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.presentation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _accessToken = MutableStateFlow<UiState<AccessToken?>?>(null)
    val accessToken = _accessToken.asStateFlow()

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
