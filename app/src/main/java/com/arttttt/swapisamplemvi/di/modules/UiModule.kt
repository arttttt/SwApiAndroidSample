package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.di.scopes.UiScope
import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
object UiModule {
    @JvmStatic
    @Provides
    @UiScope
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @JvmStatic
    @Provides
    @UiScope
    fun provideRouter(cicerone: Cicerone<Router>): Router {
        return cicerone.router
    }

    @JvmStatic
    @UiScope
    @Provides
    fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    @JvmStatic
    @UiScope
    @Provides
    fun provideRootCoordinator(router: Router): RootCoordinator {
        return RootCoordinator(router)
    }

    @JvmStatic
    @UiScope
    @Provides
    fun provideHeroFeature(): HeroFeature {
        return HeroFeature()
    }
}