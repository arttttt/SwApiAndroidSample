package com.arttttt.heroeslist.impl.ui.list.models

import com.arttttt.arch.view.ListItem

internal sealed class ProgressListItem : ListItem {

    object Initial : ProgressListItem()

    object Page : ProgressListItem()
}
