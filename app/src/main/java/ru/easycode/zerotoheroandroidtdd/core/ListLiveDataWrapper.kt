package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.main.BundleWrapper
import java.util.ArrayList

interface ListLiveDataWrapper {

    interface Save {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Add {
        fun add(source: CharSequence)
        fun update(value: List<CharSequence>)
    }

    interface Observe {
        fun liveData(): LiveData<List<CharSequence>>
    }

    interface Mutable : Add, Save

    interface All : Mutable, Observe

    object Base : All {

        private val liveData = SingleLiveEvent<List<CharSequence>>()

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(ArrayList(it)) }
        }

        override fun add(source: CharSequence) {
            val liveList = liveData.value?.toMutableList()?: mutableListOf()
            liveList.add(source)
            liveData.value = liveList
        }

        override fun update(value: List<CharSequence>) {
            liveData.value = value
        }

        override fun liveData() = liveData
    }
}