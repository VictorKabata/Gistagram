package com.vickikbt.gistagram.ui.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val authRepository: AuthRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _accessToken = MutableStateFlow<AccessToken?>(null)
    val accessToken = _accessToken.asStateFlow()

    private val _appTheme = MutableStateFlow<String?>(null)
    val appTheme = _appTheme.asStateFlow()

    init {
        getAccessToken()

        getAppTheme()
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            val response = authRepository.getAccessToken()
            response.collect {
                _accessToken.value = it
            }
        }
    }

    private fun getAppTheme() {
        viewModelScope.launch {
            try {
                settingsRepository.getAppTheme().collectLatest { theme ->
                    _appTheme.value = theme
                }
            } catch (e: Exception) {
                Log.e("ANDROID", "ERROR saving theme: ${e.localizedMessage}")
            }
        }
    }

}
