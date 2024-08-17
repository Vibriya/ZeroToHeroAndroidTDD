package ru.easycode.zerotoheroandroidtdd.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.AddFrameLayoutBinding

class AddFragment : AbstractFragment<AddFrameLayoutBinding, AddViewModel>(
    AddViewModel::class.java
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        binding.saveButton.setOnClickListener {
            viewModel.add(binding.addInputEditText.text.toString())
            requireActivity().supportFragmentManager.popBackStack()
        }


    }


}