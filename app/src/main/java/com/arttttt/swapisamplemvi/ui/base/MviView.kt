package com.arttttt.swapisamplemvi.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.disposables.DisposableContainer
import org.koin.core.KoinComponent

abstract class MviView<Event: UiEvent, VM: ViewModel> private constructor(
    protected val uiEvents: Relay<Event>,
    protected val states: Relay<VM>
):
    ObservableSource<Event> by uiEvents,
    Consumer<VM> by states,
    DisposableContainer by CompositeDisposable(),
    KoinComponent
{
    constructor(): this(PublishRelay.create(), PublishRelay.create())

    @get:LayoutRes
    abstract val layoutRes: Int

    protected fun Disposable.putToBag() {
        add(this)
    }

    protected fun Observable<out Event>.emitUiEvent() {
        subscribe(uiEvents).putToBag()
    }

    fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(layoutRes, container, false).apply(::onViewCreated)
    }

    open fun onViewCreated(view: View) {}
}