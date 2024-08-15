package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private val factory: ProvideViewModel by lazy { (application as ProvideViewModel) }
    private val mainViewModel by lazy {
        factory.viewModel(MainViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.Base.liveData().observe(this) { screen ->
            screen.observed(supportFragmentManager, binding.frameLayout.id)
        }

        mainViewModel.init(savedInstanceState == null)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        factory.viewModel(viewModelClass)
}

