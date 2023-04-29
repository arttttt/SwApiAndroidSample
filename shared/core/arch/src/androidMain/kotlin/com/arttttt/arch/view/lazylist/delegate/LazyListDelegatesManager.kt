package com.arttttt.arch.view.lazylist.delegate

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arttttt.arch.view.ListItem

class LazyListDelegatesManager(
    private val delegates: List<LazyListDelegate<*>>
) {

    companion object {

        private const val NO_DELEGATE_EXCEPTION = "No delegate found for item: %s"
    }

    fun getKey(item: ListItem): Any {
        return getDelegateOrThrow(item).key
    }

    fun getContentType(item: ListItem): Any {
        return getDelegateOrThrow(item).contentType
    }

    @Composable
    fun Content(context: LazyItemScope, item: ListItem, modifier: Modifier) {
        val delegate = getDelegateOrThrow(item)

        delegate.Content(
            context = context,
            modifier = modifier,
        )
    }

    private fun getDelegateOrThrow(item: ListItem): LazyListDelegate<*> {
        val delegate = delegates.find { delegate -> delegate.isForItem(item) }
        checkNotNull(delegate) {
            NO_DELEGATE_EXCEPTION.format(item::class.qualifiedName)
        }

        delegate._item = item

        return delegate
    }
}
