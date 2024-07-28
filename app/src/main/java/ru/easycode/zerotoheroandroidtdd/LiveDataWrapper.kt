package ru.easycode.zerotoheroandroidtdd

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

interface LiveDataWrapper {
    interface Save {
        fun save(bundleWrapper: BundleWrapper.Save)
    }
    interface Update {
        fun update(value: UiState)
    }
    interface Observe {
        fun liveData(): LiveData<UiState>
    }

    interface Mutable : Save, Update, Observe


    class Base : Mutable {
        private val liveData = SingleLiveEvent<UiState>()

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        @MainThread
        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> = liveData
    }
}