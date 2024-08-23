package ru.easycode.zerotoheroandroidtdd.note.edit

import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.main.Screen

data class EditNoteScreen(private val noteId: Long) :
    Screen.ReplaceBackstackBundleable(EditNoteFragment::class.java) {
    override val bundle: Bundle = EditNoteFragment.getBundle(noteId)
}
