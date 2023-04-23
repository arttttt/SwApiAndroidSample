package com.arttttt.app.heroeslist

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arttttt.app.heroeslist.data.repository.HeroesListRepositoryImpl
import com.arttttt.app.heroeslist.domain.entity.Hero
import com.arttttt.app.heroeslist.domain.store.HeroesListStore
import com.arttttt.app.heroeslist.domain.store.HeroesListStoreFactory
import com.arttttt.app.heroeslist.ui.HeroesListView
import com.arttttt.app.heroeslist.ui.HeroesListViewImpl
import com.arttttt.app.heroeslist.ui.lazylist.models.ProgressListItem
import com.arttttt.app.heroeslist.ui.models.HeroListItem
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.arch.view.ListItem
import com.arttttt.arch.view.ViewOwner
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class HeroesListComponentImpl(
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
