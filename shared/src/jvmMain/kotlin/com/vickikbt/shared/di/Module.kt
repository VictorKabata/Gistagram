package com.vickikbt.shared.di

import com.russhwolf.settings.ObservableSettings
import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }

    single {
        //val settings=AndroidSettings()
    }
}
