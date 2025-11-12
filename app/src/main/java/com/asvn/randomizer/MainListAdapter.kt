package com.asvn.randomizer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asvn.randomizer.databinding.MainListElementBinding

class MainListAdapter(val clickListener: (listId: Long) -> Unit)
    : ListAdapter<ListEntity, MainListAdapter.MainListViewHolder>(MainListDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : MainListViewHolder = MainListViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class MainListViewHolder(val binding: MainListElementBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): MainListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MainListElementBinding.inflate(layoutInflater, parent, false)
                return MainListViewHolder(binding)
            }
        }

        fun bind(item: ListEntity, clickListener: (listId: Long) -> Unit) {
            binding.mainElement = item
            binding.root.setOnClickListener { clickListener(item.id) }
        }
    }
}