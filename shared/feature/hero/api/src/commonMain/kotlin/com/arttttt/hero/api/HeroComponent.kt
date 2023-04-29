package com.arttttt.hero.api

import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer

interface HeroComponent : DecomposeComponent, EventsProducer<HeroComponent.Event> {

    sealed class Event {

        object BackPressed : Event()
    }
}