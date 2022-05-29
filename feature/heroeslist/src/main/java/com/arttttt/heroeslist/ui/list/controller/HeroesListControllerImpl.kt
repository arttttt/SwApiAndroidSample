package com.arttttt.heroeslist.ui.list.controller

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arttttt.heroeslist.domain.store.HeroesListStore
import com.arttttt.heroeslist.ui.list.view.HeroesListView
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HeroesListControllerImpl @Inject constructor(
    private val heroesListStore: HeroesListStore
) : HeroesListController {

    override fun onViewCreated(view: HeroesListView, lifecycle: Lifecycle) {
        bind(
            lifecycle = lifecycle,
            mode = BinderLifecycleMode.CREATE_DESTROY
        ) {
            heroesListStore
                .states
                .map { state ->
                    HeroesListView.Model(
                        items = state.heroes
                    )
                }
                .bindTo(view)

            view
                .events
                .map { event ->
                    when (event) {
                        is HeroesListView.UiEvent.HeroClicked -> HeroesListView.Command.ShowToast(event.id)
                    }
                }
                .bindTo(view::handleCommand)
        }
    }
}
