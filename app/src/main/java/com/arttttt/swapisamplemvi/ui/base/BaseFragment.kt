package com.arttttt.swapisamplemvi.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.transition.ChangeTransform
import androidx.transition.Transition
import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycle
import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycleOwner
import com.badoo.mvicore.ModelWatcher
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class BaseFragment<A: UiAction, S: ViewModel> private constructor(
    private val compositeDisposable: CompositeDisposable,
    protected val uiActions: Relay<A>
) : Fragment(),
    SimpleFragmentLifecycleOwner,
    Consumer<S>,
    IBackHandler,
    ObservableSource<A> by uiActions
{
    private var layoutRes: Int = 0

    constructor(@LayoutRes layoutRes: Int): this(CompositeDisposable(), PublishRelay.create()) {
        this.layoutRes = layoutRes
    }

    protected abstract val binder: BaseBindings<BaseFragment<A, S>>
    protected abstract val watcher: ModelWatcher<S>
    protected open val sharedElementTransition: Transition? = ChangeTransform()

    override var lifecycleListener: SimpleFragmentLifecycle? = null

    override fun accept(viewModel: S?) {
        if (viewModel == null) return

        watcher.invoke(viewModel)
    }


    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        sharedElementTransition?.let { transition ->
            sharedElementEnterTransition = transition
            sharedElementReturnTransition = transition
        }
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleListener = binder
        binder.setup(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewPreCreated()
        onViewCreated()
        lifecycleListener?.onViewCreated()
    }

    @CallSuper
    override fun onDestroyView() {
        lifecycleListener?.onViewDestroyed()
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

    @Suppress("UNCHECKED_CAST")
    protected fun<T> Fragment.argument(key: String, defValue: T? = null): T {
        return (arguments?.get(key) ?: defValue) as T
    }
}
