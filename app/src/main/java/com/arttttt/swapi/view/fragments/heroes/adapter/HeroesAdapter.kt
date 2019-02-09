package com.arttttt.swapi.view.fragments.heroes.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arttttt.swapi.R
import com.arttttt.swapi.model.hero.Hero
import com.arttttt.swapi.presenter.heroesadapter.HeroesAdapterContract
import org.koin.core.parameter.parametersOf
import org.koin.standalone.StandAloneContext

class HeroesAdapter(private val itemClick: (hero: Hero, sharedViewId: Int, position: Int) -> Unit): RecyclerView.Adapter<HeroesViewHolder>(), HeroesAdapterContract.View {

    private val presenter: HeroesAdapterContract.Presenter = StandAloneContext.getKoin().koinContext.get { parametersOf(this) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.hero_item, parent, false)

        return HeroesViewHolder(view) { position, sharedViewId ->
            val hero = presenter.getItemAt(position)
            itemClick(hero, sharedViewId, position)
        }
    }

    override fun getItemCount() = presenter.getUsersCount()

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) = presenter.bind(holder, position)

    fun updateList(newList: List<Hero>) = presenter.updateData(newList)

    override fun notifyAdapter() = notifyDataSetChanged()
}