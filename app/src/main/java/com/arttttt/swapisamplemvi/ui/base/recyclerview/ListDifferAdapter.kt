package com.arttttt.swapisamplemvi.ui.base.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import javax.inject.Inject

class ListDifferAdapter @Inject constructor(
    diffCallback: DiffUtil.ItemCallback<IListItem>,
    delegates: Set<AdapterDelegate<List<IListItem>>>
) : AsyncListDifferDelegationAdapter<IListItem>(diffCallback, *delegates.toTypedArray())
