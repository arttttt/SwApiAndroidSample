package com.arttttt.app.heroeslist.ui.lazylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arttttt.app.heroeslist.ui.models.HeroListItem
import com.arttttt.arch.view.lazylist.dsl.lazyListDelegate

fun HeroLazyListDelegate(
    onClick: (String) -> Unit,
) = lazyListDelegate<HeroListItem>(
    key = { item -> item.name },
) { modifier ->

    Text(
        modifier = Modifier
            .fillParentMaxWidth()
            .clickable { onClick.invoke(item.name) }
            .padding(
                vertical = 16.dp,
                horizontal = 8.dp,
            ),
        text = item.name,
    )
}
