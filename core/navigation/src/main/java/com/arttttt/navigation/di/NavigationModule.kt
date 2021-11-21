package com.arttttt.navigation.di

import com.arttttt.dagger2.PerFeature
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
internal abstract class NavigationModule {

    @Module
    companion object {

        @Provides
        @PerFeature
        fun provideCiceroneFactory(): () -> Cicerone<Router> {
            return { Cicerone.create() }
        }

        @Provides
        @PerFeature
        fun provideCicerone(): Cicerone<Router> {
            return Cicerone.create()
        }

        @Provides
        @PerFeature
        fun provideRouter(cicerone: Cicerone<Router>): Router {
            return cicerone.router
        }

        @Provides
        @PerFeature
        fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
            return cicerone.getNavigatorHolder()
        }

    }

}
