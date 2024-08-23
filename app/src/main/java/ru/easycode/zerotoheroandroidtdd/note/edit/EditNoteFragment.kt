package ru.easycode.zerotoheroandroidtdd.note.edit

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.AbstractComebackFragment
import ru.easycode.zerotoheroandroidtdd.databinding.EditNodeLayoutBinding

class EditNoteFragment :
    AbstractComebackFragment<EditNodeLayoutBinding, EditNoteViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteId = arguments?.getLong(KEY)?: DEFAULT_ID

        NoteLiveDataWrapper.Base.liveData.observe(viewLifecycleOwner) {
            binding.noteEditText.setText(it)
        }

        with(binding) {
            saveNoteButton.setOnClickListener {
                viewModel.renameNote(noteId, noteEditText.text.toString().trim())
            }

            deleteNoteButton.setOnClickListener {
                viewModel.deleteNote(noteId)
            }
        }

        viewModel.init(noteId)
    }

    companion object {
        private const val KEY = "KEY"
        private const val DEFAULT_ID = -1L
        fun getBundle(id: Long): Bundle =
            Bundle().apply { putLong(KEY, id) }
    }
}