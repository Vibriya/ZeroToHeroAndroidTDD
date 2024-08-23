package ru.easycode.zerotoheroandroidtdd.folder.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface FolderListLiveDataWrapper {
    interface UpdateListAndRead {
        fun update(list: List<FolderUi>)
    }

    interface Create {
        fun create(folderUi: FolderUi)
    }

    object Base : UpdateListAndRead, Create {
        private val _liveData: MutableLiveData<List<FolderUi>> = MutableLiveData()
        val liveData: LiveData<List<FolderUi>>
            get() {
                return _liveData
            }

        override fun update(list: List<FolderUi>) {
            _liveData.value = list
        }

        override fun create(folderUi: FolderUi) {
            val liveList = _liveData.value?.toMutableList()?: mutableListOf()
            liveList.add(folderUi)
            update(liveList)
        }
    }

}
