package com.arttttt.swapisamplemvi.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycle
import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycleOwner
import com.badoo.mvicore.ModelWatcher
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class BaseFragment<A: UiAction, S: ViewModel> private constructor(
    @LayoutRes layoutRes: Int,
    private val compositeDisposable: CompositeDisposable
) : KoinFragment(layoutRes),
    SimpleFragmentLifecycleOwner,
    Consumer<S>,
    IBackHandler
{
    constructor(@LayoutRes layoutRes: Int): this(layoutRes, CompositeDisposable())

    protected val binder by lazy { scope.get<BaseBindings<BaseFragment<A, S>>>() }
    val uiActions by lazy { scope.get<UiActionsDelegate<A>>() }

    override var lifecycleListener: SimpleFragmentLifecycle? = null

    private var watcher: ModelWatcher<S>? = null

    override fun accept(viewModel: S?) {
        if (viewModel == null) return

        watcher?.invoke(viewModel)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleListener = binder
        binder.setup(this)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watcher = getModelWatcher()
        onViewPreCreated()
        onViewCreated()
        lifecycleListener?.onViewCreated()
    }

    @CallSuper
    override fun onDestroyView() {
        lifecycleListener?.onViewDestroyed()
        super.onDestroyView()
        compositeDisposable.clear()
        watcher?.clear()
        watcher = null
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    protected fun Observable<out A>.bindUiAction() {
        subscribe(uiActions).untilDestroyView()
    }

    protected fun Disposable.untilDestroyView() {
        compositeDisposable.add(this)
    }

    open fun onViewPreCreated() {}
    open fun onViewCreated() {}

    protected abstract fun getModelWatcher(): ModelWatcher<S>

    @Suppress("UNCHECKED_CAST")
    protected fun<T> Fragment.argument(key: String, defValue: T? = null): T {
        return (arguments?.get(key) ?: defValue) as T
    }
}
