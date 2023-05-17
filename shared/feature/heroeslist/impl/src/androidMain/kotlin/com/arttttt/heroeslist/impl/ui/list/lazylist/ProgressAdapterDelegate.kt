package com.arttttt.heroeslist.impl.ui.list.lazylist

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arttttt.arch.view.lazylist.dsl.lazyListDelegate
import com.arttttt.heroeslist.api.ui.list.models.ProgressListItem

internal fun ProgressAdapterDelegate() = lazyListDelegate<ProgressListItem>(
    key = { item -> item::class.qualifiedName!! }
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .run {
                 when (item) {
                     is ProgressListItem.Page -> this.wrapContentHeight()
                     is ProgressListItem.Initial -> this.fillParentMaxHeight()
                 }
            },
        contentAlignment = Alignment.Center,
    ) {

        CircularProgressIndicator()
    }
}
