package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.databinding.ListFrameLayoutBinding
import ru.easycode.zerotoheroandroidtdd.main.BundleWrapper

class ListFragment :
    AbstractFragment<ListViewModel, ListFrameLayoutBinding>(ListViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            viewModel.restore(BundleWrapper.Base(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RAdapter()
        binding.recyclerView.adapter = adapter

        ListLiveDataWrapper.Base.liveData().observe(this) {
            adapter.addToList(it.map { char -> char.toString() })
        }

        binding.addButton.setOnClickListener {
            viewModel.create()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }
}