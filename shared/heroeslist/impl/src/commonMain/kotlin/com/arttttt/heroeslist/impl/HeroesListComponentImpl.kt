package com.arttttt.heroeslist.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arttttt.heroeslist.impl.data.repository.HeroesListRepositoryImpl
import com.arttttt.heroeslist.impl.domain.store.HeroesListStore
import com.arttttt.heroeslist.impl.domain.store.HeroesListStoreFactory
import com.arttttt.heroeslist.impl.ui.HeroesListView
import com.arttttt.heroeslist.impl.ui.HeroesListViewImpl
import com.arttttt.heroeslist.impl.ui.models.ProgressListItem
import com.arttttt.heroeslist.impl.ui.models.HeroListItem
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.arch.view.ListItem
import com.arttttt.arch.view.ViewOwner
import com.arttttt.heroeslist.api.HeroesListComponent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class HeroesListComponentImpl(
    componentContext: ComponentContext,
    private val eventsDelegate: EventsProducerDelegate<HeroesListComponent.Event> = EventsProducerDelegate()
) : ComponentContext by componentContext,
    HeroesListComponent,
    ViewOwner<HeroesListView>,
    EventsProducer<HeroesListComponent.Event> by eventsDelegate {

    override val view: HeroesListView by ViewOwner.create { HeroesListViewImpl(this) }::view

    init {
        val store = HeroesListStoreFactory(
            storeFactory = LoggingStoreFactory(
                delegate = DefaultStoreFactory()
            ),
            heroesListRepository = HeroesListRepositoryImpl(),
            stateKeeper = stateKeeper,
            instanceKeeper = instanceKeeper,
        ).create()

        bind(
            lifecycle = lifecycle,
            mode = BinderLifecycleMode.CREATE_DESTROY,
        ) {
            store
                .states
                .map { state ->
                    HeroesListView.Model(
                        items = let {
                            val items = mutableListOf<ListItem>()

                            state.heroes.mapTo(items) { hero ->
                                HeroListItem(
                                    name = hero.name
                                )
                            }

                            when {
                                state.isLoading && state.heroes.isEmpty() -> items += ProgressListItem.Initial
                                state.isLoading -> items += ProgressListItem.Page
                            }

                            items.toImmutableList()
                        }
                    )
                }
                .bindTo(view::render)

            view
                .events
                .filterIsInstance<HeroesListView.UiEvent.NewPageRequired>()
                .map { HeroesListStore.Intent.LoadHeroes }
                .bindTo(store)

            view
                .events
                .filterIsInstance<HeroesListView.UiEvent.HeroClicked>()
                .map { event ->
                    store.state.heroes.find { it.name == event.name }
                }
                .filterNotNull()
                .map { hero ->
                    HeroesListComponent.Event.HeroClicked(hero)
                }
                .bindTo(eventsDelegate::dispatch)
        }
    }
}
