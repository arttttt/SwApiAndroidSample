package com.arttttt.heroeslist.impl.components.heroinfo

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate
import com.arttttt.arch.view.ViewOwner
import com.arttttt.heroeslist.api.Hero
import com.arttttt.heroeslist.impl.ui.heroinfo.HeroInfoView
import com.arttttt.heroeslist.impl.ui.heroinfo.HeroInfoViewImpl
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map

internal class HeroInfoComponentImpl(
    componentContext: ComponentContext,
    hero: Hero,
    private val eventsProducerDelegate: EventsProducerDelegate<HeroInfoComponent.Event> = EventsProducerDelegate(),
) : ComponentContext by componentContext,
    HeroInfoComponent,
    EventsProducer<HeroInfoComponent.Event> by eventsProducerDelegate,
    ViewOwner<HeroInfoView> {

    override val view: HeroInfoView by ViewOwner.create(::HeroInfoViewImpl)::view

    init {
        view.render(
            HeroInfoView.Model(
                title = "Additional information",
                message = "Hero name: ${hero.name}",
            )
        )

        bind(
            lifecycle = lifecycle,
            mode = BinderLifecycleMode.CREATE_DESTROY
        ) {
            view
                .events
                .filterIsInstance<HeroInfoView.UiEvent.Dismissed>()
                .map { HeroInfoComponent.Event.Dismissed }
                .bindTo(eventsProducerDelegate::dispatch)
        }
    }
}
