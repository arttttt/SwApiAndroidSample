package com.arttttt.recyclerview

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ListDifferAdapter(
    diffCallback: DiffUtil.ItemCallback<ListItem>,
    delegates: Set<AdapterDelegate<List<ListItem>>>
) : AsyncListDifferDelegationAdapter<ListItem>(diffCallback, *delegates.toTypedArray()) {

    var remainingItemsThreshold = 3
    var loadMoreCallback: LoadMoreCallback? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any?>) {
        if (position >= items.size - remainingItemsThreshold) {
            loadMoreCallback?.loadMore()
        }
        super.onBindViewHolder(holder, position, payloads)
    }
}
