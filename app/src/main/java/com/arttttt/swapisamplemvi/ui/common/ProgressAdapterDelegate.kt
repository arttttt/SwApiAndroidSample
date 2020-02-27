package com.arttttt.swapisamplemvi.ui.common

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer

fun ProgressAdapterDelegate() = adapterDelegateLayoutContainer<ProgressAdapterItem, IListItem>(R.layout.item_progress) {}