package com.arttttt.swapisamplemvi.ui.heroeslist.di

import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.badoo.mvicore.android.AndroidTimeCapsule
import dagger.BindsInstance
import dagger.Component

@HeroesListScope
@Component(
    modules = [
        HeroesListModule::class
    ],
    dependencies = [
        HeroesListDependencies::class
    ]
)
interface HeroesListComponent {

    fun inject(fragment: HeroesListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: HeroesListDependencies,
            @BindsInstance timeCapsule: AndroidTimeCapsule
        ): HeroesListComponent
    }
}