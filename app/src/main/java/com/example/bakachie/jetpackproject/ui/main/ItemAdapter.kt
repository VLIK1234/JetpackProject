package com.example.bakachie.jetpackproject.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.bo.Item
import kotlinx.android.synthetic.main.item_adapter.view.*

class ItemAdapter(private val itemList : List<Item>, private val clickListener: ItemClickListener)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(item : View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_adapter, parent, false), clickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemLabel.text = itemList[position].item.toString()
        holder.itemView.tag = position
    }

    class ViewHolder(itemView: View, clickListener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener({
                clickListener.onItemClick(it)
            })
        }

        val itemLabel : TextView = itemView.item_label
    }
}