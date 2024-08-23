package ru.easycode.zerotoheroandroidtdd.note.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.folder.details.NoteUi

interface NoteLiveDataWrapper {
    fun update(noteText: String)

    object Base : NoteLiveDataWrapper {
        private val _liveData: MutableLiveData<String> = MutableLiveData()
        val liveData: LiveData<String>
            get() {
                return _liveData
            }
        override fun update(noteText: String) {
            _liveData.value = noteText
        }
    }
}
