package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.ClearViewModel

class App : Application(), ProvideViewModel, ClearViewModel {
    private lateinit var viewModelsFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        viewModelsFactory = ViewModelFactory.Base(ProvideViewModel.Base(this))
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>) =
        viewModelsFactory.viewModel(viewModelClass)

    override fun clear(viewModelClass: Class<out ViewModel>) {
        viewModelsFactory.clear(viewModelClass)
    }

}