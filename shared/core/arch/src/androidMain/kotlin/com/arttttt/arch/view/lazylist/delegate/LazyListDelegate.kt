package com.arttttt.arch.view.lazylist.delegate

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arttttt.arch.view.ListItem

abstract class LazyListDelegate<T : ListItem> {

    private object Uninitialized

    internal var _item: Any = Uninitialized

    /**
     * Get the current bound item.
     */
    val item: T
        get() = if (_item === Uninitialized) {
            throw IllegalArgumentException(
                "Item has not been set yet"
            )
        } else {
            @Suppress("UNCHECKED_CAST")
            _item as T
        }

    open val contentType: Any
        get() {
            return item::class
        }

    abstract val key: Any

    abstract fun isForItem(item: ListItem): Boolean

    @Composable
    abstract fun Content(context: LazyItemScope, modifier: Modifier)
}
