package com.arttttt.app.heroeslist.ui.lazylist.models

import com.arttttt.arch.view.ListItem

sealed class ProgressListItem : ListItem {

    object Initial : ProgressListItem()

    object Page : ProgressListItem()
}
