package com.vickikbt.gistagram.ui.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.utils.UiState
import kotlinx.coroutines.launch

class AuthViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _userProfile =
        MutableLiveData<UiState<AccessToken?>>()
    val userProfile: LiveData<UiState<AccessToken?>> get() = _userProfile

    fun fetchAccessToken(code: String) {
        _userProfile.postValue(UiState.Loading())

        viewModelScope.launch {
            try {
                val response = authRepository.fetchAccessToken(code = code)
                _userProfile.postValue(UiState.Success(data = response))
            } catch (e: Exception) {
                _userProfile.postValue(UiState.Error(error = e.localizedMessage))
            }
        }
    }

}
