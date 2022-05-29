package com.arttttt.main.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.main.MainFeatureApi
import com.arttttt.main.MainFeatureDependencies
import com.arttttt.main.ui.MainActivity
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        MainFeatureDependencies::class
    ],
    modules = [
        MainFeatureModule::class
    ]
)
internal interface MainFeatureComponent : MainFeatureApi {

    @Component.Factory
    interface Factory {

        fun create(
            dependencies: MainFeatureDependencies
        ): MainFeatureComponent
    }

    fun inject(activity: MainActivity)
}
