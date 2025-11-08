package com.asvn.randomizer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListItemAdapter : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {
    var data = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : ListItemViewHolder = ListItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ListItemViewHolder(val rootView: TextView)
        : RecyclerView.ViewHolder(rootView) {

            companion object {
                fun inflateFrom(parent: ViewGroup): ListItemViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val view = layoutInflater
                        .inflate(R.layout.list_item, parent, false) as TextView
                    return ListItemViewHolder(view)
                }
            }

        fun bind(item: Item) {
            rootView.text = item.name
        }
    }
}