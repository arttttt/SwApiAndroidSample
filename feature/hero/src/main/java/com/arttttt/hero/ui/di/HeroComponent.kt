package com.arttttt.hero.ui.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arttttt.dagger2.PerScreen
import com.arttttt.hero.ui.platform.HeroFragment
import dagger.BindsInstance
import dagger.Component

@PerScreen
@Component(
    dependencies = [
        HeroDependencies::class
    ],
    modules = [
        HeroModule::class
    ]
)
internal interface HeroComponent {

    fun inject(fragment: HeroFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: HeroDependencies,
            @BindsInstance stateKeeper: StateKeeper,
            @BindsInstance @HeroId heroId: Int,
            @BindsInstance instanceKeeper: InstanceKeeper
        ): HeroComponent
    }

}
