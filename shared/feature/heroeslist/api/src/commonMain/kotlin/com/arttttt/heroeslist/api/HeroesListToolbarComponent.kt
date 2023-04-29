package com.arttttt.heroeslist.api

import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.view.ComponentView
import com.arttttt.arch.view.ViewOwner

interface HeroesListToolbarComponent : DecomposeComponent,
    EventsProducer<HeroesListToolbarComponent.Event>,
    ViewOwner<ComponentView<*, *>> {

    sealed class Event {

        object BackPressed : Event()
    }
}
