package com.arttttt.arch.events

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class EventsProducerDelegate<Event: Any> : EventsProducer<Event> {

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow(extraBufferCapacity = 1)
    override val events: Flow<Event> = _events

    fun dispatch(event: Event) {
        _events.tryEmit(event)
    }
}
