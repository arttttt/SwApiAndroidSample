package com.arttttt.heroeslist.impl.components.toolbar

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.heroeslist.api.components.HeroesListToolbarComponent
import com.arttttt.heroeslist.api.ui.HeroesListToolbarView

internal class HeroesListToolbarComponentImpl(
    componentContext: ComponentContext,
    viewFactory: () -> HeroesListToolbarView,
    private val eventsDelegate: EventsProducerDelegate<HeroesListToolbarComponent.Event> = EventsProducerDelegate()
) : ComponentContext by componentContext,
    HeroesListToolbarComponent,
    EventsProducer<HeroesListToolbarComponent.Event> by eventsDelegate {

    override val view = viewFactory.invoke()

    init {
        view.render(
            model = HeroesListToolbarView.Model(
                title = "Heroes list"
            )
        )
    }
}
