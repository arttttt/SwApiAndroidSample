package com.arttttt.swapisamplemvi.ui.herodetails.di

import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsBindings
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsFragment
import org.koin.dsl.module

val heroDetailsModule = module {
    scope<HeroDetailsFragment> {
        scoped<BaseBindings<*>> {
            HeroDetailsBindings(
                heroFeature = get(),
                rootCoordinator = get()
            )
        }
    }
}