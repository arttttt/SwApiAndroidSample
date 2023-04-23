package com.arttttt.app.heroeslist.ui.lazylist

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arttttt.app.heroeslist.ui.lazylist.models.ProgressListItem
import com.arttttt.arch.view.lazylist.dsl.lazyListDelegate

fun ProgressAdapterDelegate() = lazyListDelegate<ProgressListItem>(
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
