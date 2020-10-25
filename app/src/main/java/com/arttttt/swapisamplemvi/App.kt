package com.arttttt.swapisamplemvi

import android.app.Application
import com.arttttt.swapisamplemvi.di.components.AppComponent
import com.arttttt.swapisamplemvi.di.components.DaggerAppComponent
import timber.log.Timber

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
        initTimber()
    }

    private fun initDagger() {
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}