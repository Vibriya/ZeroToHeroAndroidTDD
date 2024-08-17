package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel, ClearViewModel {
    private lateinit var viewModelsFactory: ViewModelFactory
    private lateinit var core: Core

    override fun onCreate() {
        super.onCreate()
        core = Core.Base(this)
        viewModelsFactory = ViewModelFactory.Base(ProvideViewModel.Base(core, this))
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>) =
        viewModelsFactory.viewModel(viewModelClass)

    override fun clearViewModel(clasz: Class<out ViewModel>) {
        viewModelsFactory.clearViewModel(clasz)
    }

}