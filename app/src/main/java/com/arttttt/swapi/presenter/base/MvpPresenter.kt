package com.arttttt.swapi.presenter.base

interface MvpPresenter<T: MvpView> {

    var mView: T?

    fun attachView(view: T) {
        mView = view
    }

    fun detachView() {
        mView = null
    }
}