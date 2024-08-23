package ru.easycode.zerotoheroandroidtdd.main

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface Navigation {

    interface Update {
        fun update(screen: Screen)
    }

    interface Observe {
        fun liveData(): LiveData<Screen>
    }

    interface Mutable : Update, Observe

    object Base : Mutable {
        private val liveData = SingleLiveEvent<Screen>()

        @MainThread
        override fun update(screen: Screen) {
            liveData.value = screen
        }

        override fun liveData() = liveData

    }

}
