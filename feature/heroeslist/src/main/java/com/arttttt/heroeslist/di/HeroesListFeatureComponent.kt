package com.arttttt.heroeslist.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.heroeslist.HeroesListFeatureApi
import com.arttttt.heroeslist.HeroesListFeatureDependencies
import com.arttttt.heroeslist.ui.di.HeroesListDependencies
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        HeroesListFeatureDependencies::class
    ],
    modules = [
        HeroesListFeatureModule::class
    ]
)
internal interface HeroesListFeatureComponent : HeroesListFeatureApi, HeroesListDependencies {

    @Component.Factory
    interface Factory {
        fun create(dependencies: HeroesListFeatureDependencies): HeroesListFeatureComponent
    }

}
