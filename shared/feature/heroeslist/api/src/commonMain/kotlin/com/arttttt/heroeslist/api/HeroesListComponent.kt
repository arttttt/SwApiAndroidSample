package com.arttttt.heroeslist.api

import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer

interface HeroesListComponent : DecomposeComponent, EventsProducer<HeroesListComponent.Event> {

    val toolbarComponent: HeroesListToolbarComponent

    sealed class Event {

        data class HeroClicked(val hero: Hero) : Event()
    }
}
