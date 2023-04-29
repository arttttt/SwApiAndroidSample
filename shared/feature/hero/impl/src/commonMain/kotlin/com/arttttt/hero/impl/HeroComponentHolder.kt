package com.arttttt.hero.impl

import com.arttttt.hero.api.HeroFeatureApi
import com.arttttt.hero.api.HeroFeatureDependencies
import com.arttttt.moduleinjector.ComponentHolder
import com.arttttt.moduleinjector.ComponentHolderDelegate

object HeroComponentHolder : ComponentHolder<HeroFeatureApi, HeroFeatureDependencies> {

    private val delegate = ComponentHolderDelegate { _: HeroFeatureDependencies ->
        object : HeroFeatureApi {
            override val heroComponentBuilder = HeroComponentBuilderImpl()
        }
    }

    override var dependencyProvider by delegate::dependencyProvider

    override val api: HeroFeatureApi by delegate::api
}
