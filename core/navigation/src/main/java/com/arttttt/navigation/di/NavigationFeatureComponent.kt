package com.arttttt.navigation.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.navigation.NavigationFeatureApi
import com.arttttt.navigation.NavigationFeatureDependencies
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        NavigationFeatureDependencies::class
    ],
    modules = [
        NavigationModule::class
    ]
)
internal interface NavigationFeatureComponent : NavigationFeatureApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: NavigationFeatureDependencies): NavigationFeatureComponent
    }

}
