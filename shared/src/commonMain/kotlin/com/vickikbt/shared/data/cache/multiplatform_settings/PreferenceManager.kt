@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalSettingsApi::class)

package com.vickikbt.shared.data.cache.multiplatform_settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PreferenceManager constructor(private val observableSettings: ObservableSettings) {

    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }

    fun getString(key: String) = observableSettings.getStringOrNullFlow(key = key)

    fun setInt(key: String, value: Int) {
        observableSettings.set(key = key, value = value)
    }

    fun getInt(key: String) = observableSettings.getIntOrNullFlow(key = key)

    fun clearPreferences() = observableSettings.clear()

    companion object {
        const val APP_THEME_KEY = "app_theme_key"
    }
}
