package com.arttttt.navigation.di

import com.alphicc.brick.TreeRouter
import com.arttttt.dagger2.PerFeature
import dagger.Module
import dagger.Provides

@Module
internal abstract class NavigationFeatureModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFeature
        fun provideRouter(): TreeRouter {
            return TreeRouter.new()
        }
    }
}
