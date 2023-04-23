/*
package com.arttttt.arch.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer
import com.arttttt.arch.events.EventsProducerDelegate

*/
/**
 * todo: think how to make it usable
 *//*

class ComponentViewDelegate<C: DecomposeComponent, Model: Any, Event: Any> private constructor(
    private val eventsProducerDelegate: EventsProducerDelegate<Event>,
    private val initialState: Model,
): ComponentView<C, Model, Event>, EventsProducer<Event> by eventsProducerDelegate {

    constructor(initialState: Model) : this(
        eventsProducerDelegate = EventsProducerDelegate(),
        initialState = initialState,
    )

    private val _models: MutableValue<Model> by lazy {
        MutableValue(
            initialValue = initialState,
        )
    }

    override fun render(model: Model) {
        _models.update {
            model
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val model by _models.subscribeAsState()

        */
/*Content(
            model = model,
            modifier = modifier,
        )*//*

    }

    */
/*@Composable
    protected abstract fun Content(
        model: Model,
        modifier: Modifier,
    )*//*


    protected fun dispatch(event: Event) {
        eventsProducerDelegate.dispatch(event)
    }
}
*/
