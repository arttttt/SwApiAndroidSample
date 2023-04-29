package com.arttttt.arch.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.arttttt.arch.events.EventsProducer

actual interface ComponentView<in Model : Any, out Event : Any> : EventsProducer<Event>, ViewRenderer<Model> {

    @Composable
    fun Content(modifier: Modifier)
}
