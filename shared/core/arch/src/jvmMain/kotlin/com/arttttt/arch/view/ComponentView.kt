package com.arttttt.arch.view

import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.arttttt.arch.component.ComponentHolder
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.events.EventsProducer

actual interface ComponentView<in Model : Any, out Event : Any> : EventsProducer<Event>, ViewRenderer<Model>