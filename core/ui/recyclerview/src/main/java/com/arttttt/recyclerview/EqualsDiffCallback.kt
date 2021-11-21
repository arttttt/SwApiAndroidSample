package com.arttttt.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

open class EqualsDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
        return newItem
    }
}
