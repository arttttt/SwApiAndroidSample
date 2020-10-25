package com.arttttt.swapisamplemvi.ui.base.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

open class EqualsDiffCallback: DiffUtil.ItemCallback<IListItem>() {
    override fun areItemsTheSame(oldItem: IListItem, newItem: IListItem): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: IListItem, newItem: IListItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: IListItem, newItem: IListItem): Any? {
        return newItem
    }
}
