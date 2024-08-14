package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.RAdapter
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.create.ListViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val factory by lazy { (application as ProvideViewModel) }
    private val mainViewModel by lazy { factory.viewModel(MainViewModel::class.java) }
    private val listViewModelContainer by lazy { ListViewModelContainer(factory) }
    private val createViewModelContainer by lazy { CreateViewModelContainer(factory) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.Base.liveData().observe(this) { screen ->
            screen.observed(
                this,
                binding.root,
                layoutInflater,
                createViewModelContainer,
                listViewModelContainer
            )
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                createViewModelContainer.viewModel?.comeback()
            }
        })

        mainViewModel.init(savedInstanceState == null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        listViewModelContainer.viewModel?.save(BundleWrapper.Base(outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        listViewModelContainer.viewModel?.restore(BundleWrapper.Base(savedInstanceState))
    }
}

abstract class ViewModelContainer<T : ViewModel>(
    private val factory: ProvideViewModel
) {
    var viewModel: T? = null
    abstract val clazz: Class<T>

    fun initializeViewModel() {
        viewModel = factory.viewModel(clazz)
    }
}


class CreateViewModelContainer(
    factory: ProvideViewModel
) : ViewModelContainer<CreateViewModel>(factory) {
    override val clazz: Class<CreateViewModel> = CreateViewModel::class.java
}

class ListViewModelContainer(
    factory: ProvideViewModel
) : ViewModelContainer<ListViewModel>(factory) {
    override val clazz: Class<ListViewModel> = ListViewModel::class.java
}

