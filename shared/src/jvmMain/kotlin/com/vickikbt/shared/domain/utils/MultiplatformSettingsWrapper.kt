package com.vickikbt.shared.domain.utils

import com.russhwolf.settings.JvmPreferencesSettings
import com.russhwolf.settings.ObservableSettings
import java.util.prefs.Preferences

actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): ObservableSettings {
        val preferences: Preferences = Preferences.systemRoot()
        return JvmPreferencesSettings(delegate = preferences)
    }
}
