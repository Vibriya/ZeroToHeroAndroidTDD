package ru.easycode.zerotoheroandroidtdd.folder.create

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.AbstractComebackFragment
import ru.easycode.zerotoheroandroidtdd.databinding.CreateFolderLayoutBinding
//todo pop backstack
class CreateFolderFragment :
    AbstractComebackFragment<CreateFolderLayoutBinding, CreateFolderViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveFolderButton.setOnClickListener {
            viewModel.createFolder(binding.createFolderEditText.text.toString().trim())
        }
    }
}