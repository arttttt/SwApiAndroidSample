package com.arttttt.heroeslist.ui.list.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arttttt.dagger2.PerScreen
import com.arttttt.heroeslist.ui.list.controller.HeroesListController
import dagger.BindsInstance
import dagger.Component

@PerScreen
@Component(
    dependencies = [
        HeroesListDependencies::class
    ],
    modules = [
        HeroesListModule::class
    ]
)
internal interface HeroesListComponent {

    @Component.Factory
    interface Factory {

        fun create(
            dependencies: HeroesListDependencies,
            @BindsInstance stateKeeper: StateKeeper,
            @BindsInstance instanceKeeper: InstanceKeeper
        ): HeroesListComponent
    }

    val controller: HeroesListController
}
