package ru.easycode.zerotoheroandroidtdd.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.AddFrameLayoutBinding
import ru.easycode.zerotoheroandroidtdd.databinding.DeleteFrameLayoutBinding

class DeleteFragment : AbstractFragment<DeleteFrameLayoutBinding, DeleteViewModel>(
    DeleteViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentId: Long = arguments?.getLong(KEY, DEFAULT_VALUE) ?: DEFAULT_VALUE

        viewModel.init(currentId)

        binding.deleteButton.setOnClickListener {
            viewModel.delete(currentId)
            requireActivity().supportFragmentManager.popBackStack()
        }

        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.itemTitleTextView.text = it
        }

    }

    companion object {
        private const val KEY = "DeleteFragmentArg"
        private const val DEFAULT_VALUE = -1L

        fun newInstance(id: Long): DeleteFragment {
            return DeleteFragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY, id)
                }
            }
        }
    }
}