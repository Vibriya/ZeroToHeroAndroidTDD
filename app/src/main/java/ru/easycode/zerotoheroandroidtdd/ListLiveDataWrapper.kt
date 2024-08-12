package ru.easycode.zerotoheroandroidtdd

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.ArrayList

interface ListLiveDataWrapper {
    fun liveData(): LiveData<List<CharSequence>>
    fun add(new: CharSequence)
    fun save(bundle: BundleWrapper.Save)
    fun update(list: List<CharSequence>)

    class Base : ListLiveDataWrapper {

        private val liveData: MutableLiveData<List<CharSequence>> = MutableLiveData(
            listOf()
        )
        override fun liveData(): LiveData<List<CharSequence>> = liveData

        override fun add(new: CharSequence) {
            val liveList = liveData.value?.toMutableList()?: mutableListOf()
            liveList.add(new)
            liveData.value = liveList
        }

        override fun save(bundle: BundleWrapper.Save) {
            liveData.value?.let { bundle.save(ArrayList(it)) }
        }

        @MainThread
        override fun update(list: List<CharSequence>) {
            liveData.value = list
        }
    }
}
