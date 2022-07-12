package com.vickikbt.shared.data.cache.multiplatform_settings

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class PreferenceManager constructor(private val observableSettings: ObservableSettings) {

    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }

    fun getString(key: String) = observableSettings.getStringOrNullFlow(key = key)

    fun setInt(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }

    fun getInt(key: String) = observableSettings.getStringOrNullFlow(key = key)


}
