package com.arttttt.swapisamplemvi.di.components

import com.arttttt.swapisamplemvi.di.dependencies.UiComponentDependencies
import com.arttttt.swapisamplemvi.di.modules.UiModule
import com.arttttt.swapisamplemvi.di.scopes.UiScope
import com.arttttt.swapisamplemvi.ui.AppActivity
import com.arttttt.swapisamplemvi.ui.herodetails.di.HeroDetailDependencies
import com.arttttt.swapisamplemvi.ui.heroeslist.di.HeroesListDependencies
import dagger.Component

@UiScope
@Component(
    modules = [
        UiModule::class
    ],
    dependencies = [
        UiComponentDependencies::class
    ]
)
interface UiComponent : HeroesListDependencies, HeroDetailDependencies {

    fun inject(activity: AppActivity)

    @Component.Factory
    interface Factory {
        fun create(dependencies: UiComponentDependencies): UiComponent
    }
}