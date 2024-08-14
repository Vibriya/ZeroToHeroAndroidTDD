package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.create.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.main.Navigation

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    @Suppress("UNCHECKED_CAST")
    class Base(
        private val clear: ClearViewModel
    ) : ProvideViewModel {

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when(viewModelClass) {
                MainViewModel::class.java -> MainViewModel(Navigation.Base)
                ListViewModel::class.java -> ListViewModel(ListLiveDataWrapper.Base, Navigation.Base)
                CreateViewModel::class.java -> CreateViewModel(ListLiveDataWrapper.Base, Navigation.Base, clear)
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            } as T
        }

    }
}
