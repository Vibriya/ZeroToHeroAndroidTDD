package ru.easycode.zerotoheroandroidtdd.folder.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi

interface FolderLiveDataWrapper {
    interface Increment {
        fun increment()
    }

    interface Decrement {
        fun decrement()
    }

    interface Rename {
        fun rename(newName: String)
    }

    interface Update {
        fun update(folder: FolderUi)
    }

    interface Mutable {
        fun update(folder: FolderUi)
        fun folderId(): Long
    }

    object Base : Increment, Decrement, Rename, Mutable, Update {
        private const val DEFAULT_ID = -1L

        private val _liveData: MutableLiveData<FolderUi> = MutableLiveData()
        val liveData: LiveData<FolderUi>
            get() {
                return _liveData
            }

        override fun increment() {
            _liveData.value?.let {
                update(it.apply { notesCount++ })
            }
        }

        override fun decrement() {
            _liveData.value?.let {
                update(it.apply { notesCount-- })
            }
        }

        override fun rename(newName: String) {
            _liveData.value?.title = newName
        }

        override fun update(folder: FolderUi) {
            _liveData.value = folder
        }

        override fun folderId(): Long =
            _liveData.value?.id?:DEFAULT_ID
    }

}
