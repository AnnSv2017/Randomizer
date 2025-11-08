package com.asvn.randomizer

import androidx.recyclerview.widget.DiffUtil

class ListItemDiffItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item)
        = (oldItem.name == newItem.name)

    override fun areContentsTheSame(oldItem: Item, newItem: Item)
        = (oldItem == newItem)
}