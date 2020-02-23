package com.arttttt.swapisamplemvi.di.factories

import androidx.recyclerview.widget.DiffUtil
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import javax.inject.Inject
import javax.inject.Provider

class ListDifferAdapterFactory @Inject constructor(
    private val callback: Provider<DiffUtil.ItemCallback<IListItem>>,
    private val delegates: Provider<Set<AdapterDelegate<List<IListItem>>>>
) {
    fun create(): ListDifferAdapter {
        return ListDifferAdapter(
            diffCallback = callback.get(),
            delegates = delegates.get()
        )
    }
}