package com.arttttt.swapi.view.fragments.heroes.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.arttttt.swapi.presenter.heroesadapter.HeroesAdapterContract
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.hero_item.*

class HeroesViewHolder(override val containerView: View,
                       itemClick: (position: Int, sharedViewId: Int) -> Unit): RecyclerView.ViewHolder(containerView),
    LayoutContainer,
    HeroesAdapterContract.ViewHolder {

    init {
        containerView.setOnClickListener { itemClick(adapterPosition, heroName.id) }
    }

    override fun setName(name: String) {
        heroName.text = name
    }
}