package com.arttttt.swapi.presenter.herodetails

class HeroDetailsPresenter: HeroDetailsContract.Presenter {
    override var mView: HeroDetailsContract.View? = null

    override fun initialize() {
        mView?.let { view ->
            view.initializeUI()
        }
    }
}