package com.vickikbt.gistagram

import android.app.Application
import com.vickikbt.gistagram.di.cacheModule
import com.vickikbt.gistagram.di.networkModule
import com.vickikbt.gistagram.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class GistagramApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        startKoin {
            val modules = listOf(cacheModule, networkModule, presentationModule)

            androidLogger(Level.NONE)
            androidContext(this@GistagramApplication)
            modules(modules)
        }
    }

}