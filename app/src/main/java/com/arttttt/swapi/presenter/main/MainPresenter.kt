package com.arttttt.swapi.presenter.main

class MainPresenter: MainContract.Presenter {
    override var mView: MainContract.View? = null

    override fun initialize() {
        mView?.let { view ->
            view.initializeFragment()
        }
    }
}