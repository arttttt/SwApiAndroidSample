package com.arttttt.heroeslist.impl.components.heroinfo

import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer

internal interface HeroInfoComponent : DecomposeComponent, EventsProducer<HeroInfoComponent.Event> {

    sealed class Event {
        object Dismissed : Event()
    }
}
