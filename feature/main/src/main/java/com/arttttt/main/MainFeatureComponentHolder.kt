package com.arttttt.main

import com.arttttt.main.di.DaggerMainFeatureComponent
import com.arttttt.main.di.MainFeatureComponent
import com.arttttt.moduleinjector.ComponentHolder
import com.arttttt.moduleinjector.ComponentHolderDelegate

object MainFeatureComponentHolder : ComponentHolder<MainFeatureApi, MainFeatureDependencies> {

    private val delegate = ComponentHolderDelegate<MainFeatureApi, MainFeatureDependencies, MainFeatureComponent> { dependencies ->
        DaggerMainFeatureComponent
            .factory()
            .create(dependencies)
    }

    override var dependencyProvider by delegate::dependencyProvider

    override val api: MainFeatureApi
        get() = delegate.api

    internal val component: MainFeatureComponent by delegate::component
}
