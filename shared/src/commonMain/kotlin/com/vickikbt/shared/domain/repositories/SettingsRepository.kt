package com.vickikbt.shared.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun saveAppTheme(theme: Int)

    suspend fun getAppTheme(): Flow<Int?>
}
