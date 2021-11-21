package com.arttttt.hero.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.hero.HeroFeatureApi
import com.arttttt.hero.HeroFeatureDependencies
import com.arttttt.hero.ui.di.HeroDependencies
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        HeroFeatureDependencies::class
    ],
    modules = [
        HeroFeatureModule::class
    ]
)
internal interface HeroFeatureComponent : HeroFeatureApi, HeroDependencies {

    @Component.Factory
    interface Factory {
        fun create(dependencies: HeroFeatureDependencies): HeroFeatureComponent
    }

}
