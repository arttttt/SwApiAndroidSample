package com.arttttt.mvisamplecompose

import android.app.Application
import com.alphicc.brick.Screen
import com.alphicc.brick.TreeRouter
import com.arttttt.heroeslist.HeroesListComponentHolder
import com.arttttt.heroeslist.HeroesListFeatureApi
import com.arttttt.heroeslist.HeroesListFeatureDependencies
import com.arttttt.main.MainFeatureApi
import com.arttttt.main.MainFeatureComponentHolder
import com.arttttt.main.MainFeatureDependencies
import com.arttttt.moduleinjector.*
import com.arttttt.navigation.NavigationFeatureApi
import com.arttttt.navigation.NavigationFeatureComponentHolder
import com.arttttt.navigation.NavigationFeatureDependencies
import com.arttttt.swapi.SwApiComponentHolder
import com.arttttt.swapi.SwApiFeatureApi
import com.arttttt.swapi.data.api.SwApi

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        SwApiComponentHolder.dependencyProvider = {
            DependencyHolder0<BaseFeatureDependencies> { holder ->
                object : BaseFeatureDependencies {
                    override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies>
                        get() = holder
                }
            }.dependencies
        }

        NavigationFeatureComponentHolder.dependencyProvider = {
            DependencyHolder0<NavigationFeatureDependencies> { holder ->
                object : NavigationFeatureDependencies {
                    override val dependencyHolder = holder
                }
            }.dependencies
        }

        MainFeatureComponentHolder.dependencyProvider = {
            DependencyHolder2<HeroesListFeatureApi, NavigationFeatureApi, MainFeatureDependencies>(
                api1 = HeroesListComponentHolder.api,
                api2 = NavigationFeatureComponentHolder.api
            ) { holder, heroesListApi, navigationApi ->
                object : MainFeatureDependencies {
                    override val dependencyHolder = holder

                    override val router: TreeRouter
                        get() = navigationApi.router

                    override val rootScreen: Screen<*>
                        get() = heroesListApi.heroesListScreen
                }
            }.dependencies
        }

        HeroesListComponentHolder.dependencyProvider = {
            DependencyHolder2<NavigationFeatureApi, SwApiFeatureApi, HeroesListFeatureDependencies>(
                api1 = NavigationFeatureComponentHolder.api,
                api2 = SwApiComponentHolder.api
            ) { holder, mainFeatureApi, swApiApi ->
                object : HeroesListFeatureDependencies {
                    override val dependencyHolder = holder

                    override val router: TreeRouter
                        get() = mainFeatureApi.router

                    override val swApi: SwApi
                        get() = swApiApi.swApi
                }
            }.dependencies
        }
    }
}
