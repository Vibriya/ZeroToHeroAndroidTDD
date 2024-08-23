package ru.easycode.zerotoheroandroidtdd.folder.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi

interface NoteListLiveDataWrapper {
    interface Create {
        fun create(noteUi: NoteUi)
    }

    interface Update {
        fun update(noteId: Long, newText: String)
    }

    interface UpdateListAndRead {
        fun update(notes: List<NoteUi>)
    }

    object Base : Create, Update, UpdateListAndRead {
        private val _liveData: MutableLiveData<List<NoteUi>> = MutableLiveData()
        val liveData: LiveData<List<NoteUi>>
            get() {
                return _liveData
            }
        override fun create(noteUi: NoteUi) {
            val liveList = _liveData.value?.toMutableList()?: mutableListOf()
            liveList.add(noteUi)
            update(liveList)
        }

        override fun update(noteId: Long, newText: String) {
            val liveList = _liveData.value?.toMutableList()?: mutableListOf()
            liveList.find { it.id == noteId }?.title = newText
            update(liveList)
        }

        override fun update(notes: List<NoteUi>) {
            _liveData.value = notes
        }
    }

}
