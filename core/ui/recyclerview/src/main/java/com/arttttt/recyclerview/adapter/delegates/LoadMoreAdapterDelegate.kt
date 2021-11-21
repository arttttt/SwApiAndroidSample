package com.arttttt.recyclerview.adapter.delegates

import com.arttttt.recyclerview.ListItem
import com.arttttt.recyclerview.R
import com.arttttt.recyclerview.adapter.models.LoadMoreAdapterItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun LoadMoreAdapterDelegate() = adapterDelegate<LoadMoreAdapterItem, ListItem>(R.layout.item_load_more) {}
