package com.arttttt.hero.api

import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.view.ViewOwner

interface HeroComponent : DecomposeComponent,
    EventsProducer<HeroComponent.Event>,
    ViewOwner<HeroView> {

    sealed class Event {

        object BackPressed : Event()
    }
}
