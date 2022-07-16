package com.vickikbt.shared.domain.utils

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings

actual class MultiplatformSettingsWrapper(private val context: Context) {
    actual fun createSettings(): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("gistagram_preferences", Context.MODE_PRIVATE)
        return AndroidSettings(delegate = sharedPreferences)
    }
}
