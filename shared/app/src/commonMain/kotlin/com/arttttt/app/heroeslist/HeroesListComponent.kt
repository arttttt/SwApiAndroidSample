package com.arttttt.app.heroeslist

import com.arttttt.app.heroeslist.domain.entity.Hero
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer

interface HeroesListComponent : DecomposeComponent, EventsProducer<HeroesListComponent.Event> {

    sealed class Event {

        data class HeroClicked(val hero: Hero) : Event()
    }
}
