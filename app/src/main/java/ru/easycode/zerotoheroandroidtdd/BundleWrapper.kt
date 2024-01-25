package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

interface BundleWrapper {
    interface Mutable : Save, Restore

    interface Save {
        fun save(state: UiState)
    }

    interface Restore {
        fun restore() : UiState
    }

    @Suppress("DEPRECATION")
    class Base(
        private val bundle: Bundle,
        private val key: String = "BundleWrapperSavedState"
    ) : Mutable {
        override fun save(state: UiState) {
            bundle.putSerializable(key, state)
        }

        override fun restore(): UiState =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                bundle.getSerializable(key, UiState::class.java)!!
            else
                bundle.getSerializable(key) as UiState
    }

}
