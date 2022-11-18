package com.example.fetchrewardsassignment

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context, private val items: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val listId = itemView.findViewById<TextView>(R.id.listId)
        private val itemName = itemView.findViewById<TextView>(R.id.name)

        fun bind(item: Item){
                listId.text = item.listId.toString()
                itemName.text = item.name.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.bind(item)

    }

    override fun getItemCount() = items.size

}