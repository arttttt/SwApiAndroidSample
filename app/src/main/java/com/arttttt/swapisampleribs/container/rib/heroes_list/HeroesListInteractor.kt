package com.arttttt.swapisampleribs.container.rib.heroes_list

import androidx.lifecycle.Lifecycle
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature
import com.arttttt.swapisampleribs.container.rib.heroes_list.mapper.InputToWish
import com.arttttt.swapisampleribs.container.rib.heroes_list.mapper.NewsToOutput
import com.arttttt.swapisampleribs.container.rib.heroes_list.mapper.StateToViewModel
import com.arttttt.swapisampleribs.container.rib.heroes_list.mapper.ViewEventToWish
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams

internal class HeroesListInteractor(
    buildParams: BuildParams<*>,
    private val feature: HeroesListFeature
) : Interactor<HeroesList, heroesListView>(
    buildParams = buildParams,
    disposables = feature
) {

    override fun onAttach(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            bind(feature.news to rib.output using NewsToOutput)
            bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: heroesListView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(feature to view using StateToViewModel)
            bind(view to feature using ViewEventToWish)
        }
    }
}
