package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RAdapter(
    private val list: MutableList<String> = mutableListOf()
) : RecyclerView.Adapter<RAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.elementTextView)

        fun bind(text: String) {
            textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addToList(item: String) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun addToList(itemList: List<String>) {
        list.addAll(itemList)
        notifyDataSetChanged()
    }

    fun save(bundle: Bundle) {
        bundle.putStringArrayList(
            KEY,
            ArrayList(list)
        )
    }

    fun restore(bundle: Bundle) {
        val restoredList = bundle.getStringArrayList(KEY)
        restoredList?.let { list.addAll(it) }
    }

    companion object {
        private const val KEY = "KEY"
    }
}