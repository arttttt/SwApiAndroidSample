package com.arttttt.mvikotlinsample

import android.app.Application
import android.content.Context
import com.arttttt.hero.HeroComponentHolder
import com.arttttt.hero.HeroFeatureApi
import com.arttttt.hero.HeroFeatureDependencies
import com.arttttt.heroeslist.HeroesListComponentHolder
import com.arttttt.heroeslist.HeroesListFeatureApi
import com.arttttt.heroeslist.HeroesListFeatureDependencies
import com.arttttt.main.MainFeatureComponentHolder
import com.arttttt.main.MainFeatureDependencies
import com.arttttt.navigation.NavigationComponentHolder
import com.arttttt.navigation.NavigationFeatureApi
import com.arttttt.navigation.NavigationFeatureDependencies
import com.ewa.module_injector.DependencyHolder0
import com.ewa.module_injector.DependencyHolder2

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MainFeatureComponentHolder.dependencyProvider = {
            DependencyHolder2<HeroesListFeatureApi, NavigationFeatureApi, MainFeatureDependencies>(
                api1 = HeroesListComponentHolder.get(),
                api2 = NavigationComponentHolder.get()
            ) { holder, heroesListApi, navigationApi ->
                object : MainFeatureDependencies {
                    override val dependencyHolder = holder
                    override val heroesListFeatureEntryPoint = heroesListApi.heroesListFeatureEntryPoint
                    override val router = navigationApi.router
                    override val navigatorHolder = navigationApi.navigatorHolder
                }
            }.dependencies
        }

        HeroesListComponentHolder.dependencyProvider = {
            DependencyHolder2<HeroFeatureApi, NavigationFeatureApi, HeroesListFeatureDependencies>(
                api1 = HeroComponentHolder.get(),
                api2 = NavigationComponentHolder.get()
            ) { holder, heroApi, navigationApi ->
                object : HeroesListFeatureDependencies {
                    override val dependencyHolder = holder
                    override val heroFeatureEntryPoint = heroApi.heroFeatureEntryPoint
                    override val router = navigationApi.router
                    override val context = this@App
                }
            }.dependencies
        }

        HeroComponentHolder.dependencyProvider = {
            DependencyHolder0<HeroFeatureDependencies> { holder ->
                object : HeroFeatureDependencies {
                    override val dependencyHolder = holder
                }
            }.dependencies
        }

        NavigationComponentHolder.dependencyProvider = {
            DependencyHolder0<NavigationFeatureDependencies> { holder ->
                object : NavigationFeatureDependencies {
                    override val dependencyHolder = holder
                }
            }.dependencies
        }
    }

}
