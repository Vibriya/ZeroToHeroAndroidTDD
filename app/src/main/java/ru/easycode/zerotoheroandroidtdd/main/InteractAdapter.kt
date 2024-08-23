package ru.easycode.zerotoheroandroidtdd.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.folder.details.NoteUi
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi
import java.lang.reflect.ParameterizedType

abstract class InteractAdapter<T : ItemUi>(
    private val listener: (T) -> Unit,
) : RecyclerView.Adapter<InteractAdapter.ViewHolder<T>>() {
    private val list: MutableList<T> = mutableListOf()
    abstract val viewHolderClass: Class<out ViewHolder<T>>
    abstract val layoutId: Int

    abstract class ViewHolder<T : ItemUi>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T, listener: (T) -> Unit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return viewHolderClass.getDeclaredConstructor(View::class.java).newInstance(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(list[position], listener)
    }

    fun add(itemList: List<T>) {
        list.clear()
        list.addAll(itemList)
        notifyDataSetChanged()
    }
}

class FoldersAdapter(listener: (FolderUi) -> Unit,) : InteractAdapter<FolderUi>(listener){
    override val viewHolderClass: Class<out ViewHolder<FolderUi>> = ViewHolderFolderUi::class.java
    override val layoutId: Int = R.layout.recycler_item_folder

    class ViewHolderFolderUi(itemView: View) : ViewHolder<FolderUi>(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.folderTitleTextView)
        private val textViewCount = itemView.findViewById<TextView>(R.id.folderCountTextView)

        override fun bind(item: FolderUi, listener: (FolderUi) -> Unit) {
            item.bind(textView, textViewCount)
            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }
}

class NotesAdapter(listener: (NoteUi) -> Unit,) : InteractAdapter<NoteUi>(listener){
    override val viewHolderClass: Class<out ViewHolder<NoteUi>> = ViewHolderNoteUi::class.java
    override val layoutId: Int = R.layout.recycler_item

    class ViewHolderNoteUi(itemView: View) : ViewHolder<NoteUi>(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.noteTitleTextView)

        override fun bind(item: NoteUi, listener: (NoteUi) -> Unit) {
            item.bind(textView)
            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }
}
