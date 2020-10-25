package com.arttttt.swapisamplemvi.ui.herodetails.di

import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsFragment
import dagger.Component

@HeroDetailScope
@Component(
    modules = [
        HeroDetailModule::class
    ],
    dependencies = [
        HeroDetailDependencies::class
    ]
)
interface HeroDetailsComponent {

    fun inject(fragment: HeroDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: HeroDetailDependencies): HeroDetailsComponent
    }
}