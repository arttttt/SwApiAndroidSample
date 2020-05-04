package com.arttttt.swapisamplemvi.ui.base.extensions

import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.common.ProgressAdapterItem

fun MutableList<IListItem>.addLoadingMore() {
    this += ProgressAdapterItem
}