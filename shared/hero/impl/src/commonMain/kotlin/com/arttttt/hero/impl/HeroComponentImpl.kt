package com.arttttt.hero.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.arch.view.ViewOwner
import com.arttttt.hero.api.HeroComponent
import com.arttttt.hero.impl.ui.HeroView
import com.arttttt.hero.impl.ui.HeroViewImpl
import com.arttttt.heroeslist.api.Hero
import kotlinx.coroutines.flow.filterIsInstance

internal class HeroComponentImpl(
    componentContext: ComponentContext,
    hero: Hero,
    private val eventsDelegate: EventsProducerDelegate<HeroComponent.Event> = EventsProducerDelegate()
) : ComponentContext by componentContext,
    HeroComponent,
    ViewOwner<HeroView>,
    EventsProducer<HeroComponent.Event> by eventsDelegate {

    override val view: HeroView by ViewOwner.create { HeroViewImpl() }::view

    init {
        view.render(
            HeroView.Model(
                title = hero.name,
            )
        )

        bind(
            lifecycle = lifecycle,
            mode = BinderLifecycleMode.CREATE_DESTROY,
        ) {

            view
                .events
                .filterIsInstance<HeroView.UiEvent.BackPressed>()
                .bindTo {
                    eventsDelegate.dispatch(HeroComponent.Event.BackPressed)
                }
        }
    }
}
