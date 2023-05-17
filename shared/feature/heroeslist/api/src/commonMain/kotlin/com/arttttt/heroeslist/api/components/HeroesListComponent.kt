package com.arttttt.heroeslist.api.components

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.view.ViewOwner
import com.arttttt.heroeslist.api.entity.Hero
import com.arttttt.heroeslist.api.ui.list.HeroesListView

interface HeroesListComponent : DecomposeComponent,
    EventsProducer<HeroesListComponent.Event>,
    ViewOwner<HeroesListView> {

    val toolbarComponent: HeroesListToolbarComponent
    val dialogSlot: Value<ChildSlot<*, HeroInfoComponent>>

    sealed class Event {

        data class HeroClicked(val hero: Hero) : Event()
    }
}
