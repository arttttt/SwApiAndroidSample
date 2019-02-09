package com.arttttt.swapi.presenter.heroesadapter

import com.arttttt.swapi.model.hero.Hero
import com.arttttt.swapi.presenter.base.MvpView

interface HeroesAdapterContract {
    interface View: MvpView {
        fun notifyAdapter()
    }

    interface Presenter: MvpView {
        fun bind(holder: ViewHolder, position: Int)
        fun getItemAt(position: Int): Hero
        fun getUsersCount(): Int
        fun updateData(newHeroes: List<Hero>)
    }

    interface ViewHolder {
        fun setName(name: String)
    }
}