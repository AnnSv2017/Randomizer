package com.asvn.randomizer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asvn.randomizer.databinding.ListItemBinding

class ListItemAdapter
    : ListAdapter<Item, ListItemAdapter.ListItemViewHolder>(ListItemDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : ListItemViewHolder = ListItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ListItemViewHolder(val binding: ListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ListItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ListItemViewHolder(binding)
            }
        }

        fun bind(item: Item) {
            binding.item = item
        }
    }
}