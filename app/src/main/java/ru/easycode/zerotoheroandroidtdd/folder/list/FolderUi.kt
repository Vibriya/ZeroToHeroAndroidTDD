package ru.easycode.zerotoheroandroidtdd.folder.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.main.InteractAdapter
import ru.easycode.zerotoheroandroidtdd.main.ItemUi

data class FolderUi(
    val id: Long,
    var title: String,
    var notesCount: Int
) : ItemUi {

    override fun bind(vararg views: TextView) {
        views[0].text = title
        views[1].text = notesCount.toString()
    }
}
