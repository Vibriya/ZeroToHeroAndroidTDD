package ru.easycode.zerotoheroandroidtdd.folder.edit

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.AbstractComebackFragment
import ru.easycode.zerotoheroandroidtdd.databinding.EditFolderLayoutBinding
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi

class EditFolderFragment :
    AbstractComebackFragment<EditFolderLayoutBinding, EditFolderViewModel>() {
    private lateinit var folder: FolderUi

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FolderLiveDataWrapper.Base.liveData.observe(viewLifecycleOwner) {
            binding.folderEditText.setText(it.title)
            folder = it
        }

        binding.deleteFolderButton.setOnClickListener {
            viewModel.deleteFolder(folder.id)
        }
        binding.saveFolderButton.setOnClickListener {
            viewModel.renameFolder(folder.id, binding.folderEditText.text.toString().trim())
        }
    }
}