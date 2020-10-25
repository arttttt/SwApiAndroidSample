package com.arttttt.swapisamplemvi.ui.heroeslist.adapter

import com.arttttt.swapisamplemvi.ui.base.recyclerview.EqualsDiffCallback
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.models.HeroAdapterItem

class HeroesListDiffCallback : EqualsDiffCallback() {
    override fun areItemsTheSame(oldItem: IListItem, newItem: IListItem): Boolean {
        return when {
            newItem is HeroAdapterItem && oldItem is HeroAdapterItem -> newItem.name == oldItem.name
            else -> super.areItemsTheSame(oldItem, newItem)
        }
    }
}