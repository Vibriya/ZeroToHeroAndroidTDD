package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel, ClearViewModels {
    private lateinit var viewModelsFactory: ProvideViewModel
    private lateinit var core: ProvideViewModel

    override fun onCreate() {
        super.onCreate()
        core = ProvideViewModel.Base(this, this)
        viewModelsFactory = ProvideViewModel.Factory(core)
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>) =
        viewModelsFactory.viewModel(clasz)

    override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
        (viewModelsFactory as ClearViewModels).clear(*viewModelClasses)
    }

}