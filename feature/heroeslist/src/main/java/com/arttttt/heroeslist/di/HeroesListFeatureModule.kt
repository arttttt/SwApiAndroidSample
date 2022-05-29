package com.arttttt.heroeslist.di

import com.alphicc.brick.Screen
import com.arttttt.dagger2.PerFeature
import com.arttttt.heroeslist.ui.list.HeroesListScreen
import dagger.Module
import dagger.Provides

@Module
internal abstract class HeroesListFeatureModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFeature
        fun provideScreen(component: HeroesListFeatureComponent): Screen<*> {
            return HeroesListScreen(
                dependencies = component
            )
        }
    }
}
