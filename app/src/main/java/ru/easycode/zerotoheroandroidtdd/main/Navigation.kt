package ru.easycode.zerotoheroandroidtdd.main

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface Navigation {

    interface Update {
        fun update(value: Screen)
    }

    interface Observe {
        fun liveData(): LiveData<Screen>
    }

    interface Mutable : Update, Observe

    object Base : Mutable {
        private val liveData = MutableLiveData<Screen>()

        @MainThread
        override fun update(value: Screen) {
            liveData.value = value
        }

        override fun liveData() = liveData

    }

}
