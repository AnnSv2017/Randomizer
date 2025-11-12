package com.asvn.randomizer

import androidx.recyclerview.widget.DiffUtil

class MainListDiffItemCallback : DiffUtil.ItemCallback<ListEntity>() {
    override fun areItemsTheSame(oldItem: ListEntity, newItem: ListEntity)
        = (oldItem.name == newItem.name)

    override fun areContentsTheSame(oldItem: ListEntity, newItem: ListEntity)
        = (oldItem == newItem)
}