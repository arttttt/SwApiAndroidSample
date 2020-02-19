package com.arttttt.swapisamplemvi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import com.badoo.mvicore.android.AndroidBindings
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class BaseFragment<A: UiAction, S: ViewModel> private constructor(
    private val compositeDisposable: CompositeDisposable,
    protected val uiActions: Relay<A>,
    protected val states: Relay<S>
) : Fragment(),
    IBackHandler,
    ObservableSource<A> by uiActions,
    Consumer<S> by states
{

    private var layoutRes: Int = 0

    constructor(@LayoutRes layoutRes: Int): this(CompositeDisposable(), PublishRelay.create(), PublishRelay.create()) {
        this.layoutRes = layoutRes
    }

    protected abstract val binder: AndroidBindings<BaseFragment<A, S>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.setup(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewPreCreated()
        onViewCreated()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    protected fun Observable<out A>.emitUiAction() {
        subscribe(uiActions).add()
    }

    protected fun Disposable.add() {
        compositeDisposable.add(this)
    }

    open fun onViewPreCreated() {}
    open fun onViewCreated() {}

    protected fun<T> Fragment.argument(key: String, defValue: T? = null): T {
        return return (arguments?.get(key) ?: defValue) as T
    }
}
