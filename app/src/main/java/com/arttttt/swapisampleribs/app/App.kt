package com.arttttt.swapisampleribs.app

import android.app.Application
import com.arttttt.swapisampleribs.di.apiModule
import com.arttttt.swapisampleribs.di.appModule
import com.arttttt.swapisampleribs.di.repositoryModule
import com.arttttt.swapisampleribs.rib.bottom_navigation.builder.bottomNavigationModule
import com.arttttt.swapisampleribs.rib.heroes_list.builder.heroesListModule
import com.arttttt.swapisampleribs.rib.movies_list.builder.moviesListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    appModule,
                    apiModule,
                    repositoryModule,
                    heroesListModule,
                    moviesListModule,
                    bottomNavigationModule
                )
            )
        }
    }
}