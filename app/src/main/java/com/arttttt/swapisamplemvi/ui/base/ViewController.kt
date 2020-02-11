package com.arttttt.swapisamplemvi.ui.base

import android.view.View
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*
import org.koin.core.KoinComponent

abstract class ViewController<Event: UiEvent, VM: ViewModel> private constructor(
    protected val uiEvents: Relay<Event>,
    protected val states: Relay<VM>,
    protected val disposableBag: CompositeDisposable
):
    ObservableSource<Event> by uiEvents,
    Consumer<VM> by states,
    LayoutContainer,
    KoinComponent
{
    constructor(): this(PublishRelay.create(), PublishRelay.create(), CompositeDisposable())

    override var containerView: View? = null

    protected fun Disposable.putToBag() {
        disposableBag.add(this)
    }

    protected fun Observable<out Event>.emitUiEvent() {
        subscribe(uiEvents).putToBag()
    }

    fun attachView(view: View) {
        containerView = view
        onViewCreated()
    }

    fun detachView() {
        onViewDestroyed()
        disposableBag.clear()
        clearFindViewByIdCache()
        containerView = null
    }

    open fun onViewCreated() {}

    protected open fun onViewDestroyed() {}
}