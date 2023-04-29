package com.arttttt.arch.view.lazylist.delegate

import com.arttttt.arch.view.ListItem

abstract class LazyListDelegate<T : ListItem> {

    abstract fun getContentType(item: T): Any
    abstract fun getKey(item: T): Any

    abstract fun isForItem(item: ListItem): Boolean

    abstract fun createViewHolder(item: T): LazyListViewHolder<T>
}
