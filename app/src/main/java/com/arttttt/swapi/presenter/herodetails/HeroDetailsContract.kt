package com.arttttt.swapi.presenter.herodetails

import com.arttttt.swapi.presenter.base.MvpPresenter
import com.arttttt.swapi.presenter.base.MvpView

interface HeroDetailsContract {
    interface Presenter: MvpPresenter<View> {
        fun initialize()
    }

    interface View: MvpView {
        fun initializeUI()
    }
}