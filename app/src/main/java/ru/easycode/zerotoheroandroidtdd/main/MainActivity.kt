package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private val factory by lazy { (application as ProvideViewModel) }
    private val mainViewModel by lazy { factory.viewModel(MainViewModel::class.java) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.Base.liveData().observe(this) {
            it.consume(supportFragmentManager, binding.rootLayout.id)
        }

        mainViewModel.init(savedInstanceState == null)
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T =
        factory.viewModel(clasz)
}