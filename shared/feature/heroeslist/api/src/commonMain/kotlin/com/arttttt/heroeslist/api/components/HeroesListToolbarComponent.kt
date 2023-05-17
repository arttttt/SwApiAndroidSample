package com.arttttt.heroeslist.api.components

import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.view.ViewOwner
import com.arttttt.heroeslist.api.ui.HeroesListToolbarView

interface HeroesListToolbarComponent : DecomposeComponent,
    EventsProducer<HeroesListToolbarComponent.Event>,
    ViewOwner<HeroesListToolbarView> {

    sealed class Event {

        object BackPressed : Event()
    }
}
