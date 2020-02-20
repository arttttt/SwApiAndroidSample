package com.arttttt.swapisamplemvi.ui.base.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

open class DefaultDiffCallback @Inject constructor(): DiffUtil.ItemCallback<IListItem>() {
    override fun areItemsTheSame(oldItem: IListItem, newItem: IListItem): Boolean {
        return oldItem::class == newItem::class
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: IListItem, newItem: IListItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: IListItem, newItem: IListItem): Any? {
        return newItem
    }
}
