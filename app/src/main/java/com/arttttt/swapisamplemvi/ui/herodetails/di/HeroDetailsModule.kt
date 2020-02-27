package com.arttttt.swapisamplemvi.ui.herodetails.di

import com.arttttt.swapisamplemvi.di.PerFragment
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsBindings
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsFragment
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsViewModel
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import dagger.Module
import dagger.Provides

@Module
class HeroDetailsModule {

    @PerFragment
    @Provides
    fun provideHeroDetailsBindings(
        fragment: HeroDetailsFragment,
        heroFeature: HeroFeature,
        rootCoordinator: RootCoordinator
    ): BaseBindings<BaseFragment<HeroDetailsFragment.HeroDetailsUiAction, HeroDetailsViewModel>> {
        return HeroDetailsBindings(
            lifecycleOwner = fragment,
            heroFeature = heroFeature,
            rootCoordinator = rootCoordinator
        ).unsafeCastTo()
    }
}