package com.vickikbt.shared.data.data_source

import com.vickikbt.shared.data.cache.multiplatform_settings.PreferenceManager

import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl constructor(private val preferenceManager: PreferenceManager) :
    SettingsRepository {

    override suspend fun saveAppTheme(theme: Int) {
        preferenceManager.setInt(key = PreferenceManager.APP_THEME_KEY, value = theme)
    }

    override suspend fun getAppTheme(): Flow<Int?> {
        return preferenceManager.getInt(key = PreferenceManager.APP_THEME_KEY)
    }
}
