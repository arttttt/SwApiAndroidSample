package com.arttttt.swapisamplemvi

import android.app.Application
import com.arttttt.swapisamplemvi.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(appModules)
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}