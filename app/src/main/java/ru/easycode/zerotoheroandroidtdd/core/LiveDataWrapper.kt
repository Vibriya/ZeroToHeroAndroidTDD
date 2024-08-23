package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.main.ItemUi

interface LiveDataWrapper {

    interface Observe {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Update {
        fun update(item: ItemUi)
    }

    interface All : Observe, Update

}