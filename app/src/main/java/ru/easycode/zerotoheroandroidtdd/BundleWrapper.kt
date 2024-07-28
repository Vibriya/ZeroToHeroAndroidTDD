package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

interface BundleWrapper {
    interface Save {
        fun save(state: UiState)
    }

    interface Restore {
        fun restore() : UiState
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle,
        private val key: String = "DefKey"
    ) : Mutable {

        override fun save(state: UiState) {
            bundle.putSerializable(key, state)
        }

        override fun restore() : UiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(key, UiState::class.java)
        } else {
            bundle.getSerializable(key)
        }  as UiState
    }

}
