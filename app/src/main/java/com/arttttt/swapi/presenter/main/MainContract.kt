package com.arttttt.swapi.presenter.main

import com.arttttt.swapi.presenter.base.MvpPresenter
import com.arttttt.swapi.presenter.base.MvpView

interface MainContract {
    interface Presenter: MvpPresenter<View> {
        fun initialize()
    }
    interface View: MvpView {
        fun initializeFragment()
    }
}