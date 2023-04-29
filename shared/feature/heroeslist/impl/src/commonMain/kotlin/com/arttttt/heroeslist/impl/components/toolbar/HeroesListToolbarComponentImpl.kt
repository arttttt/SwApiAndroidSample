package com.arttttt.heroeslist.impl.components.toolbar

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.arch.view.ViewOwner
import com.arttttt.heroeslist.api.HeroesListToolbarComponent
import com.arttttt.heroeslist.impl.ui.toolbar.HeroesListToolbarView
import com.arttttt.heroeslist.impl.ui.toolbar.HeroesListToolbarViewImpl

internal class HeroesListToolbarComponentImpl(
    componentContext: ComponentContext,
    private val eventsDelegate: EventsProducerDelegate<HeroesListToolbarComponent.Event> = EventsProducerDelegate()
) : ComponentContext by componentContext,
    HeroesListToolbarComponent,
    EventsProducer<HeroesListToolbarComponent.Event> by eventsDelegate {

    override val view by ViewOwner.create(::HeroesListToolbarViewImpl)::view

    init {
        view.render(
            model = HeroesListToolbarView.Model(
                title = "Heroes list"
            )
        )
    }
}
