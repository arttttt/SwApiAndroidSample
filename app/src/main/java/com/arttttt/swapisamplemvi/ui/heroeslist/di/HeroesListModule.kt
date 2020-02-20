package com.arttttt.swapisamplemvi.ui.heroeslist.di

import com.arttttt.swapisamplemvi.di.PerFragment
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListBinding
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListViewModel
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import com.badoo.mvicore.android.AndroidBindings
import dagger.Module
import dagger.Provides

@Module
class HeroesListModule {

    @PerFragment
    @Provides
    fun provideHeroesListBindings(
        fragment: HeroesListFragment,
        feature: HeroesFeature,
        coordinator: RootCoordinator
    ): AndroidBindings<BaseFragment<HeroesListFragment.HeroesListUiAction, HeroesListViewModel>> {
        return HeroesListBinding(
            lifecycleOwner = fragment,
            heroesFeature = feature,
            coordinator = coordinator
        ).unsafeCastTo()
    }
}