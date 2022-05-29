package com.arttttt.swapi.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.moduleinjector.BaseFeatureDependencies
import com.arttttt.swapi.SwApiFeatureApi
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        BaseFeatureDependencies::class
    ],
    modules = [
        SwApiFeatureModule::class
    ]
)
internal interface SwApiFeatureComponent : SwApiFeatureApi {

    @Component.Factory
    interface Factory {

        fun create(
            dependencies: BaseFeatureDependencies
        ): SwApiFeatureComponent
    }
}
