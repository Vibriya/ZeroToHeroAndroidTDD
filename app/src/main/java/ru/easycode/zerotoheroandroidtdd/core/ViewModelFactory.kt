package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ClearViewModel, ProvideViewModel {

    @Suppress("UNCHECKED_CAST")
    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {
        private val cache: MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

        override fun clear(viewModelClass: Class<out ViewModel>) {
            cache.remove(viewModelClass)
        }

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {

            return if (cache.containsKey(viewModelClass))
                cache[viewModelClass] as T
            else {
                val viewModel = provideViewModel.viewModel(viewModelClass)
                cache[viewModelClass] = viewModel
                viewModel
            }

        }

    }
}
