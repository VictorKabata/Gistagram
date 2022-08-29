package com.vickikbt.shared.di

import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.repositories.ProfileRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(isDebug: Boolean = false, appDeclaration: KoinAppDeclaration = {}) = startKoin {
    val sharedModules = listOf(commonModule, platformModule())

    appDeclaration()
    modules(sharedModules)
}

/**
 * Called by iOS etc
 */
fun KoinApplication.Companion.start(): KoinApplication = initKoin {}

val Koin.iosSettingsRepository: SettingsRepository
    get() = get()

val Koin.iosAuthRepository: AuthRepository
    get() = get()

val Koin.iosProfileRepository: ProfileRepository
    get() = get()
