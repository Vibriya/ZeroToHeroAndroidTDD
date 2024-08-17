package ru.easycode.zerotoheroandroidtdd.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.ItemUi
import ru.easycode.zerotoheroandroidtdd.R

class RAdapter(
    private val listener: (Long) -> Unit
) : RecyclerView.Adapter<RAdapter.ViewHolder>() {
    private val list: MutableList<ItemUi> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.elementTextView)

        fun bind(item: ItemUi, listener: (Long) -> Unit) {
            textView.text = item.text
            itemView.setOnClickListener {
                listener.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    fun addToList(itemList: List<ItemUi>) {
        list.clear()
        list.addAll(itemList)
        notifyDataSetChanged()
    }
}