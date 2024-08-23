package ru.easycode.zerotoheroandroidtdd.note.create

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.AbstractComebackFragment
import ru.easycode.zerotoheroandroidtdd.databinding.CreateNoteLayoutBinding
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi

class CreateNoteFragment :
    AbstractComebackFragment<CreateNoteLayoutBinding, CreateNoteViewModel>() {
    private lateinit var folder: FolderUi

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FolderLiveDataWrapper.Base.liveData.observe(viewLifecycleOwner) {
            folder = it
        }

        with(binding) {
            saveNoteButton.setOnClickListener {
                viewModel.createNote(
                    folder.id,
                    createNoteEditText.text.toString().trim()
                )
            }
        }

    }
}