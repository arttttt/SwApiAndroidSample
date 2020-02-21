package com.arttttt.swapisamplemvi.ui.heroeslist.adapter

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_hero.*

fun HeroAdapterDelegate(
    listener: HeroItemListener
) = adapterDelegateLayoutContainer<HeroAdapterItem, IListItem>(R.layout.item_hero) {

    container.clicks().map { adapterPosition }.subscribe(listener.clicks)

    bind {
        tvHeroName.text = item.name
        tvHeroName.transitionName = item.name
    }
}

interface HeroItemListener {
    val clicks: Consumer<Int>
}