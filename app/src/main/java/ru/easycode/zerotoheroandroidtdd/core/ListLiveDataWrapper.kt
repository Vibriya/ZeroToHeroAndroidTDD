package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.main.ItemUi

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: ItemUi)
    }

    interface Observe {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Update {
        fun update(value: List<ItemUi>)
        fun update(item: ItemUi)
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

        override fun update(item: ItemUi) {
            val liveList = liveData.value?.toMutableList()?: mutableListOf()
            val oldItem: ItemUi? = liveList.find { it.id == item.id }
            val id: Int = liveList.indexOf(oldItem)
            liveList.removeAt(id)
            liveList.add(id, item)
            update(liveList)
        }

        override fun liveData() = liveData
    }
}