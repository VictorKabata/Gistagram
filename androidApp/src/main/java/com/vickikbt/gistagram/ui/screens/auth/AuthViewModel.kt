package com.vickikbt.gistagram.ui.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.gistagram.utils.UiState
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _authUiState = MutableLiveData<UiState<AccessToken?>>()
    val authUiState: LiveData<UiState<AccessToken?>> get() = _authUiState

    fun fetchAccessToken(code: String) {
        viewModelScope.launch {
            _authUiState.postValue(UiState.Loading())

            try {
                val response = authRepository.fetchAccessToken(code = code)
                _authUiState.postValue(UiState.Success(data = response))
            } catch (e: Exception) {
                _authUiState.postValue(UiState.Error(error = e.localizedMessage))
            }
        }
    }


}
