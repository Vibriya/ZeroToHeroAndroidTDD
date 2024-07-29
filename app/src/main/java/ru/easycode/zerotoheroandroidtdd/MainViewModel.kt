package ru.easycode.zerotoheroandroidtdd

import android.text.Editable
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper.Mutable
) : ViewModel() {

    fun click(text: String) {
        liveDataWrapper.update(UiState.ShowData(text))
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    @MainThread
    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }

    fun liveData() : LiveData<UiState> = liveDataWrapper.liveData()

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    LiveDataWrapper.Base()
                )
            }
        }
    }
}
