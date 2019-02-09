package com.arttttt.swapi.presenter.heroesadapter

import com.arttttt.swapi.model.hero.Hero

class HeroesAdapterPresenter(private val view: HeroesAdapterContract.View): HeroesAdapterContract.Presenter {

    private var heroes = listOf<Hero>()

    override fun bind(holder: HeroesAdapterContract.ViewHolder, position: Int) {
        val hero = getItemAt(position)

        holder.setName(hero.name)
    }

    override fun getItemAt(position: Int) = heroes[position]

    override fun getUsersCount() = heroes.size

    override fun updateData(newHeroes: List<Hero>) {
        heroes = newHeroes
        view.notifyAdapter()
    }
}