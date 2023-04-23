package com.arttttt.arch.view.lazylist.dsl

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arttttt.arch.view.lazylist.delegate.LazyListDelegate
import com.arttttt.arch.view.lazylist.delegate.LazyListDelegatesManager
import com.arttttt.arch.view.ListItem

inline fun <reified T : ListItem> lazyListDelegate(
    noinline key: (item: T) -> Any,
    noinline contentType: ((item: T) -> Any)? = null,
    noinline content: @Composable context(LazyItemScope, LazyListDelegate<T>) (modifier: Modifier) -> Unit,
) = object : LazyListDelegate<T>() {

    override val key: Any
        get() {
            return key.invoke(item)
        }

    override val contentType: Any
        get() = contentType?.invoke(item) ?: super.contentType

    override fun isForItem(item: ListItem): Boolean {
        return item is T
    }

    @Composable
    override fun Content(context: LazyItemScope, modifier: Modifier) {
        content(context, this, modifier)
    }
}

@Composable
fun rememberLazyListDelegateManager(delegates: List<LazyListDelegate<*>>): LazyListDelegatesManager {
    return remember(delegates) {
        LazyListDelegatesManager(
            delegates = delegates
        )
    }
}
