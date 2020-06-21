package com.arttttt.swapisampleribs.rib.heroes_list.view.adapter.delegates

import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.recyclerview.IListItem
import com.arttttt.swapisampleribs.rib.heroes_list.view.adapter.models.HeroAdapterItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_hero.*

fun HeroAdapterDelegate(
    onClick: () -> Unit
) = adapterDelegateLayoutContainer<HeroAdapterItem, IListItem>(R.layout.item_hero) {

    itemView.setOnClickListener { onClick() }

    bind {
        tvHeroName.text = item.name
        tvHeroName.transitionName = item.name
    }
}