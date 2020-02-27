package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.di.PerActivity
import com.arttttt.swapisamplemvi.di.PerFragment
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsFragment
import com.arttttt.swapisamplemvi.ui.herodetails.di.HeroDetailsModule
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.di.HeroesListModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
abstract class UiModule {

    @Module
    companion object {

        @JvmStatic
        @PerActivity
        @Provides
        fun provideCicerone(): Cicerone<Router> {
            return Cicerone.create()
        }

        @JvmStatic
        @PerActivity
        @Provides
        fun provideRouter(cicerone: Cicerone<Router>): Router {
            return cicerone.router
        }

        @JvmStatic
        @PerActivity
        @Provides
        fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder {
            return cicerone.navigatorHolder
        }

        @JvmStatic
        @PerActivity
        @Provides
        fun provideCoordinator(router: Router): RootCoordinator{
            return RootCoordinator(router)
        }

        @JvmStatic
        @PerActivity
        @Provides
        fun provideHeroFeature(): HeroFeature {
            return HeroFeature()
        }
    }

    @PerFragment
    @ContributesAndroidInjector(modules = [HeroesListModule::class])
    abstract fun provideHeroesListFragment(): HeroesListFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [HeroDetailsModule::class])
    abstract fun provideHeroDetailsFragment(): HeroDetailsFragment
}