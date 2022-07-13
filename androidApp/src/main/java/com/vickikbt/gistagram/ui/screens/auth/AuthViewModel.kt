package com.vickikbt.gistagram.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.User
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.presentation.UiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _userUiState = MutableStateFlow<UiState<User?>?>(null)
    val userUiState = _userUiState.asStateFlow()

    fun fetchAccessToken(code: String) {
        _userUiState.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val response = authRepository.fetchAccessToken(code = code)
                Napier.e("Fetched access token: $response")
                Napier.e("Code: $code")

                fetchUserProfile()

            } catch (e: Exception) {
                _userUiState.value = UiState.Error(error = e.localizedMessage)
            }
        }
    }

    private fun fetchUserProfile() {
        _userUiState.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val response = authRepository.fetchUserProfile()
                Napier.e("User fetched: $response")
                _userUiState.value = UiState.Success(data = response)
            } catch (e: Exception) {
                _userUiState.value = UiState.Error(error = e.localizedMessage)
            }
        }
    }

}
