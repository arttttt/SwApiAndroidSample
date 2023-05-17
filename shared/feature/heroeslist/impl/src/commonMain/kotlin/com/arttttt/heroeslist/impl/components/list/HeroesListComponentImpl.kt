package com.arttttt.heroeslist.impl.components.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.arch.extensions.slotComponentEvents
import com.arttttt.arch.view.ListItem
import com.arttttt.arch.view.ViewOwner
import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.api.entity.Hero
import com.arttttt.heroeslist.api.ui.HeroesListToolbarView
import com.arttttt.heroeslist.api.ui.list.HeroesListView
import com.arttttt.heroeslist.api.components.HeroInfoComponent
import com.arttttt.heroeslist.impl.components.heroinfo.HeroInfoComponentImpl
import com.arttttt.heroeslist.impl.components.toolbar.HeroesListToolbarComponentImpl
import com.arttttt.heroeslist.impl.data.repository.HeroesListRepositoryImpl
import com.arttttt.heroeslist.impl.domain.store.HeroesListStore
import com.arttttt.heroeslist.impl.domain.store.HeroesListStoreFactory
import com.arttttt.heroeslist.api.ui.list.models.HeroListItem
import com.arttttt.heroeslist.api.ui.list.models.ProgressListItem
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

internal class HeroesListComponentImpl(
    componentContext: ComponentContext,
    listViewFactory: (HeroesListComponent) -> HeroesListView,
    toolbarViewFactory: () -> HeroesListToolbarView,
    private val eventsDelegate: EventsProducerDelegate<HeroesListComponent.Event> = EventsProducerDelegate(),
) : ComponentContext by componentContext,
    HeroesListComponent,
    ViewOwner<HeroesListView>,
    EventsProducer<HeroesListComponent.Event> by eventsDelegate {

    sealed class DialogConfig : Parcelable {
        @Parcelize
        data class HeroInfo(
            val hero: Hero,
        ) : DialogConfig()
    }

    private val dialogNavigation = SlotNavigation<DialogConfig>()

    private val _dialog = childSlot(
        source = dialogNavigation,
    ) { config, componentContext ->
        when (config) {
            is DialogConfig.HeroInfo -> HeroInfoComponentImpl(
                componentContext = componentContext,
                hero = config.hero,
            )
        }
    }

    override val dialogSlot: Value<ChildSlot<*, HeroInfoComponent>> = _dialog

    override val toolbarComponent = HeroesListToolbarComponentImpl(
        componentContext = childContext(
            key = "toolbar_component",
        ),
        viewFactory = toolbarViewFactory,
    )

    override val view: HeroesListView = listViewFactory.invoke(this)

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
                .mapNotNull { event ->
                    store.state.heroes.find { it.name == event.name }
                }
                .map { hero ->
                    HeroesListComponent.Event.HeroClicked(hero)
                }
                .bindTo(eventsDelegate::dispatch)

            view
                .events
                .filterIsInstance<HeroesListView.UiEvent.ShowInfoClicked>()
                .mapNotNull { event ->
                    store.state.heroes.find { it.name == event.name }
                }
                .bindTo { hero ->
                    dialogNavigation.activate(
                        DialogConfig.HeroInfo(
                            hero = hero,
                        )
                    )
                }

            dialogSlot
                .slotComponentEvents<HeroInfoComponent.Event>()
                .filterIsInstance<HeroInfoComponent.Event.Dismissed>()
                .bindTo {
                    dialogNavigation.dismiss()
                }
        }
    }
}
