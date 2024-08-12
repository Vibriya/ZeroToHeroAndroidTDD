package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class MainViewModel(
    private val listLiveDataWrapper: ListLiveDataWrapper
) : ViewModel() {

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }

    fun liveData() : LiveData<List<CharSequence>> = listLiveDataWrapper.liveData()

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    ListLiveDataWrapper.Base()
                )
            }
        }
    }

}
