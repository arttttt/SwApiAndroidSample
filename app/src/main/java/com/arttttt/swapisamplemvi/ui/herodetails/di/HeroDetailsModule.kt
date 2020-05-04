package com.arttttt.swapisamplemvi.ui.herodetails.di

import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.UiActionsDelegate
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsBindings
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsFragment
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsUiActionsDelegate
import org.koin.dsl.module

val heroDetailsModule = module {
    scope<HeroDetailsFragment> {
        scoped<BaseBindings<*>> {
            HeroDetailsBindings(
                heroFeature = get(),
                rootCoordinator = get()
            )
        }

        scoped<UiActionsDelegate<*>> { HeroDetailsUiActionsDelegate() }
    }
}