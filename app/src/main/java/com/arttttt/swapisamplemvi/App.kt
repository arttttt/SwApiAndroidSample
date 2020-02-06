package com.arttttt.swapisamplemvi

import android.app.Application
import com.arttttt.swapisamplemvi.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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
            modules(appModule)
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}