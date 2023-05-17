package com.arttttt.arch.view

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate

abstract class PlatformAbstractComponentView<Model : Any, Event : Any> private constructor(
    private val eventsProducerDelegate: EventsProducerDelegate<Event>,
) : ComponentView<Model, Event>, EventsProducer<Event> by eventsProducerDelegate {

    constructor() : this(
        eventsProducerDelegate = EventsProducerDelegate(),
    )

    protected abstract val initialState: Model

    protected val models: MutableValue<Model> by lazy {
        MutableValue(
            initialValue = initialState,
        )
    }

    override fun render(model: Model) {
        models.update {
            model
        }
    }

    protected fun dispatch(event: Event) {
        eventsProducerDelegate.dispatch(event)
    }
}