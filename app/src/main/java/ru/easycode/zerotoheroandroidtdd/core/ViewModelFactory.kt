package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.ClearViewModel

interface ViewModelFactory : ClearViewModel, ProvideViewModel {

    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {
        private val cachedViewModels: MutableMap<Class<out ViewModel>, ViewModel>  = mutableMapOf()

        override fun clear(viewModelClass: Class<out ViewModel>) {
            cachedViewModels.remove(viewModelClass)
        }

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {

            return if (cachedViewModels.containsKey(viewModelClass))
                cachedViewModels[viewModelClass] as T
            else {
                val viewModel = provideViewModel.viewModel(viewModelClass)
                cachedViewModels[viewModelClass] = viewModel
                viewModel
            }

        }
    }
}
