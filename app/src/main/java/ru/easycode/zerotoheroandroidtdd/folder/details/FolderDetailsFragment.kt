package ru.easycode.zerotoheroandroidtdd.folder.details

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.AbstractComebackFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FolderDetailsLayoutBinding
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.InteractAdapter
import ru.easycode.zerotoheroandroidtdd.main.NotesAdapter

class FolderDetailsFragment
    : AbstractComebackFragment<FolderDetailsLayoutBinding, FolderDetailsViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editFolderButton.setOnClickListener {
            viewModel.editFolder()
        }
        binding.addNoteButton.setOnClickListener {
            viewModel.createNote()
        }
        val adapter = NotesAdapter {
            viewModel.editNote(it)
        }
        binding.notesRecyclerView.adapter = adapter
        NoteListLiveDataWrapper.Base.liveData.observe(viewLifecycleOwner) {
            adapter.add(it)
        }
        FolderLiveDataWrapper.Base.liveData.observe(viewLifecycleOwner) {
            with(binding) {
                folderNameTextView.text = it.title
                notesCountTextView.text = it.notesCount.toString()
            }
        }
        viewModel.init()
    }
}