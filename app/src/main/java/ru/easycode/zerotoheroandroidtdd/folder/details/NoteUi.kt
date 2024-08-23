package ru.easycode.zerotoheroandroidtdd.folder.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.main.InteractAdapter
import ru.easycode.zerotoheroandroidtdd.main.ItemUi

data class NoteUi(
    val id: Long,
    var title: String,
    val folderId: Long
) : ItemUi {
    override fun bind(vararg views: TextView) {
        views[0].text = title
    }
}
