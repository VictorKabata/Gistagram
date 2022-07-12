package com.vickikbt.gistagram.ui.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    private val _appTheme = MutableStateFlow<String?>(null)
    val appTheme = _appTheme.asStateFlow()

    init {
        getAppTheme()
    }

    fun setAppTheme(theme: String) {
        viewModelScope.launch {
            try {
                settingsRepository.saveAppTheme(theme = theme)
            } catch (e: Exception) {
                Log.e("ANDROID", "ERROR saving theme: ${e.localizedMessage}")
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
