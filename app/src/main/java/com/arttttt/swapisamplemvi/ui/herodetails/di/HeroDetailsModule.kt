package com.arttttt.swapisamplemvi.ui.herodetails.di

import com.arttttt.swapisamplemvi.di.PerFragment
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsBinding
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsFragment
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsViewModel
import com.arttttt.swapisamplemvi.utils.extensions.argument
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import com.badoo.mvicore.android.AndroidBindings
import dagger.Module
import dagger.Provides

@Module
class HeroDetailsModule {

    @PerFragment
    @Provides
    fun provideHeroFeature(
        fragment: HeroDetailsFragment,
        swRepository: SwRepository
    ): HeroFeature {
        return HeroFeature(
            heroName = fragment.argument(HeroDetailsFragment.HERO_NAME),
            swRepository = swRepository
        )
    }

    @PerFragment
    @Provides
    fun provideHeroDetailsBindings(
        fragment: HeroDetailsFragment,
        heroFeature: HeroFeature,
        rootCoordinator: RootCoordinator
    ): AndroidBindings<BaseFragment<HeroDetailsFragment.HeroDetailsUiAction, HeroDetailsViewModel>> {
        return HeroDetailsBinding(
            lifecycleOwner = fragment,
            heroFeature = heroFeature,
            rootCoordinator = rootCoordinator
        ).unsafeCastTo()
    }
}