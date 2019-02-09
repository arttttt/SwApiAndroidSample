package com.arttttt.swapi.view.fragments.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arttttt.swapi.presenter.base.MvpPresenter
import com.arttttt.swapi.presenter.base.MvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<V: MvpView,P: MvpPresenter<V>>: Fragment() {
    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected abstract fun getMvpView(): V

    protected abstract val presenter: P

    protected abstract fun initializeUI(view: View)

    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        compositeDisposable = CompositeDisposable()

        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(getMvpView())
        initializeUI(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        compositeDisposable.clear()
        presenter.detachView()
    }
}