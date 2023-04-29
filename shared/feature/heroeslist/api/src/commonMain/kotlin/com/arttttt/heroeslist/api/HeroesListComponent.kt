package com.arttttt.heroeslist.api

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.view.ViewOwner

interface HeroesListComponent : DecomposeComponent, EventsProducer<HeroesListComponent.Event> {

    val toolbarComponent: HeroesListToolbarComponent
    val dialogSlot: Value<ChildSlot<*, ViewOwner<*>>>

    sealed class Event {

        data class HeroClicked(val hero: Hero) : Event()
    }
}
