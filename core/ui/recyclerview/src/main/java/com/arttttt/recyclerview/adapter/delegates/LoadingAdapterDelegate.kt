package com.arttttt.recyclerview.adapter.delegates

import com.arttttt.recyclerview.ListItem
import com.arttttt.recyclerview.R
import com.arttttt.recyclerview.adapter.models.LoadingAdapterItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun LoadingAdapterDelegate() = adapterDelegate<LoadingAdapterItem, ListItem>(R.layout.item_loading) {}
