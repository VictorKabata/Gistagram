package com.vickikbt.shared.domain.utils

import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }
}
