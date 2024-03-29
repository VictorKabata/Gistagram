package com.vickikbt.shared.di

import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import com.vickikbt.shared.domain.utils.MultiplatformSettingsWrapper
import io.ktor.client.engine.android.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(context = get()) }

    single { MultiplatformSettingsWrapper(context = get()) }

    single { Android.create()}
}
