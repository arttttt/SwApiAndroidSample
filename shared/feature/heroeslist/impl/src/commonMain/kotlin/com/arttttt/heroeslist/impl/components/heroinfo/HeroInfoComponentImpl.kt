package com.arttttt.heroeslist.impl.components.heroinfo

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.heroeslist.api.components.HeroInfoComponent
import com.arttttt.heroeslist.api.entity.Hero

internal class HeroInfoComponentImpl(
    componentContext: ComponentContext,
    hero: Hero,
    private val eventsProducerDelegate: EventsProducerDelegate<HeroInfoComponent.Event> = EventsProducerDelegate(),
) : ComponentContext by componentContext,
    HeroInfoComponent,
    EventsProducer<HeroInfoComponent.Event> by eventsProducerDelegate {

    override val title: String = "Additional information"

    override val message: String = "Hero name: ${hero.name}"

    override fun onDismissed() {
        eventsProducerDelegate.dispatch(HeroInfoComponent.Event.Dismissed)
    }
}
