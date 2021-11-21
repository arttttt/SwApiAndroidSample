package com.arttttt.heroeslist.ui

import android.content.Context
import android.widget.Toast
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arttttt.heroeslist.domain.store.HeroesListStore
import com.arttttt.heroeslist.ui.adapter.model.HeroAdapterItem
import com.arttttt.recyclerview.ListItem
import com.arttttt.recyclerview.adapter.models.LoadMoreAdapterItem
import com.arttttt.recyclerview.adapter.models.LoadingAdapterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HeroesListControllerImpl @Inject constructor(
    private val context: Context,
    private val heroesListStore: HeroesListStore,
    private val coordinator: HeroesListCoordinator
) : HeroesListController {

    override fun onViewCreated(view: HeroesListView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, Dispatchers.Main.immediate) {
            heroesListStore
                .labels
                .bindTo { label ->
                    when (label) {
                        is HeroesListStore.Label.Notification -> Toast.makeText(
                            context,
                            label.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            view
                .events
                .filterIsInstance<HeroesListView.UiEvent.LoadMore>()
                .map { HeroesListStore.Intent.LoadMore } bindTo heroesListStore

            view
                .events
                .filterIsInstance<HeroesListView.UiEvent.HeroClicked>()
                .bindTo { event ->
                    coordinator.openHero(event.id)
                }

            heroesListStore
                .states
                .map { state ->
                    val items = mutableListOf<ListItem>()

                    when {
                        state.heroes.isEmpty() && state.isLoading -> items += LoadingAdapterItem
                        else -> {
                            state.heroes.mapTo(items) { hero ->
                                HeroAdapterItem(
                                    id = hero.id,
                                    name = hero.name
                                )
                            }

                            if (state.isLoading) {
                                items += LoadMoreAdapterItem
                            }
                        }
                    }

                    HeroesListView.Model(items = items)
                } bindTo view
        }
    }
}
