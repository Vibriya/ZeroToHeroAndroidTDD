package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.CreateFrameLayoutBinding

class CreateFragment :
    AbstractFragment<CreateViewModel, CreateFrameLayoutBinding>(CreateViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = binding.createButton
        val inputText = binding.inputEditText

        inputText.addTextChangedListener { text ->
            text?.let {
                button.isEnabled = it.length >= 3
            }
        }

        button.setOnClickListener {
            viewModel.add(inputText.text.toString())
            viewModel.comeback()
            inputText.text?.clear()
        }
    }
}