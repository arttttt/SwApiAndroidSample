package com.arttttt.arch.view

import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.arttttt.arch.events.EventsProducer
import platform.UIKit.UIViewController

actual interface ComponentView<in Model : Any, out Event : Any> : EventsProducer<Event>, ViewRenderer<Model> {

    val uiViewController: UIViewController
}