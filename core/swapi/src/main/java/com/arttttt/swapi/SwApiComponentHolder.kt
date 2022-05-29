package com.arttttt.swapi

import com.arttttt.moduleinjector.BaseFeatureDependencies
import com.arttttt.moduleinjector.ComponentHolder
import com.arttttt.moduleinjector.ComponentHolderDelegate
import com.arttttt.swapi.di.DaggerSwApiFeatureComponent
import com.arttttt.swapi.di.SwApiFeatureComponent

object SwApiComponentHolder : ComponentHolder<SwApiFeatureApi, BaseFeatureDependencies> {

    private val delegate = ComponentHolderDelegate<SwApiFeatureApi, BaseFeatureDependencies, SwApiFeatureComponent> { dependencies ->
        DaggerSwApiFeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by delegate::dependencyProvider

    override val api: SwApiFeatureApi
        get() = delegate.api
}
