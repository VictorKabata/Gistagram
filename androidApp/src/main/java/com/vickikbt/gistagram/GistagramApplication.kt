package com.vickikbt.gistagram

import android.app.Application
import com.vickikbt.gistagram.di.presentationModule
import com.vickikbt.shared.di.initKoin
import com.vickikbt.shared.domain.utils.NapierInit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class GistagramApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(presentationModule)

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@GistagramApplication)
            modules(appModules)
        }

        if (BuildConfig.DEBUG) NapierInit().init()
    }
}
