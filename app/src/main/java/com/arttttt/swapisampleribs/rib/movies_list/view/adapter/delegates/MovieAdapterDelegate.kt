package com.arttttt.swapisampleribs.rib.movies_list.view.adapter.delegates

import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.recyclerview.IListItem
import com.arttttt.swapisampleribs.rib.movies_list.view.adapter.models.MovieAdapterItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

fun MovieAdapterDelegate() = adapterDelegateLayoutContainer<MovieAdapterItem, IListItem>(R.layout.item_movie) {
    bind {
        tvMovieTitle.text = item.title
    }
}