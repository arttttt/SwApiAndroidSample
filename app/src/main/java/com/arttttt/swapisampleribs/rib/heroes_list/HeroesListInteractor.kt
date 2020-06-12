package com.arttttt.swapisampleribs.rib.heroes_list

import androidx.lifecycle.Lifecycle
import com.arttttt.swapisampleribs.rib.heroes_list.view.adapter.models.HeroAdapterItem
import com.arttttt.swapisampleribs.rib.heroes_list.feature.HeroesListFeature
import com.arttttt.swapisampleribs.rib.heroes_list.mapper.InputToWish
import com.arttttt.swapisampleribs.rib.heroes_list.mapper.NewsToOutput
import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListView
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams

internal class HeroesListInteractor(
    buildParams: BuildParams<*>,
    private val feature: HeroesListFeature
) : Interactor<HeroesList, HeroesListView>(
    buildParams = buildParams,
    disposables = feature
) {

    override fun onAttach(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            bind(feature.news to rib.output using NewsToOutput)
            bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: HeroesListView, viewLifecycle: Lifecycle) {
        viewLifecycle.createDestroy {
            bind(feature to view using { state ->
                HeroesListView.ViewModel(
                    items = state.heroes.map { hero ->
                        HeroAdapterItem(
                            name = hero.name
                        )
                    }
                )
            })
        }
    }
}
