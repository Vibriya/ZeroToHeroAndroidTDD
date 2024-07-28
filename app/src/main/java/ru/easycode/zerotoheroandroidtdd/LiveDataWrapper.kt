package ru.easycode.zerotoheroandroidtdd

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

interface LiveDataWrapper {
    fun save(bundleWrapper: BundleWrapper.Save)

    fun update(value: UiState)

    fun liveData(): LiveData<UiState>

    class Base : LiveDataWrapper {
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