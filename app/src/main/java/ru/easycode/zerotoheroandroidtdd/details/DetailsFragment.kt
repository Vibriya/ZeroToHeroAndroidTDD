package ru.easycode.zerotoheroandroidtdd.details

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.DetailsFrameLayoutBinding

class DetailsFragment : AbstractFragment<DetailsFrameLayoutBinding, DetailsViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentId: Long = arguments?.getLong(KEY, DEFAULT_VALUE) ?: DEFAULT_VALUE

        viewModel.init(currentId)

        binding.deleteButton.setOnClickListener {
            viewModel.delete(currentId)
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.updateButton.setOnClickListener {
            viewModel.update(currentId, binding.itemInputEditText.text.toString().trim())
            requireActivity().supportFragmentManager.popBackStack()
        }

        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.itemTextView.text = it
            binding.itemInputEditText.setText(it)
        }

    }

    companion object {
        private const val KEY = "DetailsFragmentArg"
        private const val DEFAULT_VALUE = -1L

        fun newInstance(id: Long): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY, id)
                }
            }
        }
    }
}