package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.add.AddFragment
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.details.DetailsFragment

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private val factory by lazy { (application as ProvideViewModel) }
    private val mainViewModel by lazy { factory.viewModel(MainViewModel::class.java) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(binding.rootLayout.id, AddFragment())
                .addToBackStack("AddFragment")
                .commit()
        }

        val adapter = RAdapter {
            supportFragmentManager.beginTransaction()
                .add(binding.rootLayout.id, DetailsFragment.newInstance(it))
                .addToBackStack("DeleteFragment")
                .commit()
        }

        binding.recyclerView.adapter = adapter

        ListLiveDataWrapper.Base.liveData().observe(this) {
            adapter.addToList(it)
        }

        mainViewModel.init()
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        factory.viewModel(viewModelClass)
}