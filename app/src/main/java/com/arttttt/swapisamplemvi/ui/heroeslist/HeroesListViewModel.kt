package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.ui.base.ViewModel
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem

data class HeroesListViewModel(
    val isLoading: Boolean,
    val items: List<IListItem>
): ViewModel