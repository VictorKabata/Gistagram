package com.vickikbt.gistagram.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _accessToken = MutableStateFlow<AccessToken?>(null)
    val accessToken: StateFlow<AccessToken?> get() = _accessToken

    init {
        getAccessToken()
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            val response = authRepository.getAccessToken()
            response.collect {
                _accessToken.value = it
            }
        }
    }

}
