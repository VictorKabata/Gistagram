package com.vickikbt.shared.data.cache.multiplatform_settings

import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import com.vickikbt.shared.domain.utils.MultiplatformSettingsWrapper


class PreferenceManager constructor(private val multiplatformSettingsWrapper: MultiplatformSettingsWrapper) {

    private val observableSettings = multiplatformSettingsWrapper.createSettings()

    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }

    fun getString(key: String) = observableSettings.getStringOrNullFlow(key = key)

    fun setInt(key: String, value: Int) {
        observableSettings.set(key = key, value = value)
    }

    fun getInt(key: String) = observableSettings.getIntOrNullFlow(key = key)

    companion object {
        const val APP_THEME_KEY = "app_theme_key"
    }

}
