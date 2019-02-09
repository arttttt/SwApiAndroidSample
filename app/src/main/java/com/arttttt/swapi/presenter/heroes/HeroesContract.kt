package com.arttttt.swapi.presenter.heroes

import com.arttttt.swapi.model.hero.Hero
import com.arttttt.swapi.presenter.base.MvpPresenter
import com.arttttt.swapi.presenter.base.MvpView

interface HeroesContract {
    interface Presenter: MvpPresenter<View> {
        fun initialize()
        fun findHero(name: String)
    }
    interface View: MvpView {
        fun hideMessage()
        fun showHeroes(heroes: List<Hero>)
        fun showLoading(show: Boolean)
        fun showMessage(message: String)
    }
}