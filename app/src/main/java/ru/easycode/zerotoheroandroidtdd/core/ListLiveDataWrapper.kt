package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.ItemUi
import ru.easycode.zerotoheroandroidtdd.main.BundleWrapper
import java.util.ArrayList

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: ItemUi)
    }

    interface Observe {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Update {
        fun update(value: List<ItemUi>)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface All : Observe, Update, Add, Delete

    object Base : All {

        private val liveData = SingleLiveEvent<List<ItemUi>>()

        override fun add(value: ItemUi) {
            val liveList = liveData.value?.toMutableList()?: mutableListOf()
            liveList.add(value)
            update(liveList)
        }

        override fun delete(item: ItemUi) {
            val liveList = liveData.value?.toMutableList()?: mutableListOf()
            liveList.remove(item)
            update(liveList)
        }

        override fun update(value: List<ItemUi>) {
            liveData.value = value
        }

        override fun liveData() = liveData
    }
}