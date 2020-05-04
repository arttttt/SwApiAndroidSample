package com.arttttt.swapisamplemvi.ui.base

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

abstract class UiActionsDelegate<A: UiAction> private constructor(
    protected val uiActions: Relay<A>
): ObservableSource<A> by uiActions, Consumer<A> by uiActions {
    constructor(): this(PublishRelay.create())
}