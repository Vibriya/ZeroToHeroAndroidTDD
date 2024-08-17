package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.main.BundleWrapper
import java.util.ArrayList

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: String)
    }

    interface Observe {
        fun liveData(): LiveData<List<String>>
    }

    interface Update {
        fun update(value: List<String>)
    }

    interface Mutable : Observe, Update

    object Base : Mutable, Add {

        private val liveData = SingleLiveEvent<List<String>>()

        override fun add(value: String) {
            val liveList = liveData.value?.toMutableList()?: mutableListOf()
            liveList.add(value)
            liveData.value = liveList
        }

        override fun update(value: List<String>) {
            liveData.value = value
        }

        override fun liveData() = liveData
    }
}