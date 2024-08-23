package ru.easycode.zerotoheroandroidtdd.core

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.viewbinding.ViewBinding

abstract class AbstractComebackFragment<VB : ViewBinding, T : ComebackViewModel>
    : AbstractFragment<VB, T>() {

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.popBackStack()
                viewModel.comeback()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}