package com.arttttt.swapi

import android.app.Application
import com.arttttt.swapi.di.AppModule
import org.koin.android.ext.android.startKoin

class SwApiApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(AppModule.module))
    }
}