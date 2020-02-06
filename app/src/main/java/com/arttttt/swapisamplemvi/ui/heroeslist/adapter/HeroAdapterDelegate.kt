package com.arttttt.swapisamplemvi.ui.heroeslist.adapter

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_hero.*

fun HeroAdapterDelegate() = adapterDelegateLayoutContainer<HeroAdapterItem, IListItem>(R.layout.item_hero) {
    bind {
        tvHeroName.text = item.name
    }
}