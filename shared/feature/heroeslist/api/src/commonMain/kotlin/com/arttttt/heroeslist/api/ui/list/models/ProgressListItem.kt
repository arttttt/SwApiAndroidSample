package com.arttttt.heroeslist.api.ui.list.models

import com.arttttt.arch.view.ListItem

sealed class ProgressListItem : ListItem() {

    object Initial : ProgressListItem() {

        override fun equals(other: Any?): Boolean {
            return false

            //return super.equals(other)
        }

        override fun hashCode(): Int {
            return 0

            //return super.hashCode()
        }
    }

    object Page : ProgressListItem() {

        override fun equals(other: Any?): Boolean {
            return false

            //return super.equals(other)
        }

        override fun hashCode(): Int {
            return 0

            //return super.hashCode()
        }
    }
}
