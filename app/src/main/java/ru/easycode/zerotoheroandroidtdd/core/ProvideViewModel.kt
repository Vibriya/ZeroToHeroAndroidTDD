package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.details.DetailsViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    @Suppress("UNCHECKED_CAST")
    class Base(
        private val core: Core,
        private val clear: ClearViewModel
    ) : ProvideViewModel {


        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(
                    core.repository(),
                    ListLiveDataWrapper.Base,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                AddViewModel::class.java -> AddViewModel(
                    core.repository(),
                    ListLiveDataWrapper.Base,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                DetailsViewModel::class.java -> DetailsViewModel(
                    ListLiveDataWrapper.Base,
                    core.repository(),
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                else -> throw IllegalArgumentException("Unknown ViewModel class")
            } as T
        }

    }
}
