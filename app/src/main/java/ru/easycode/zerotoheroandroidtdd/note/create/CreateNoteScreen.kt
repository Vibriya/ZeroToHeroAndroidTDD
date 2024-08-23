package ru.easycode.zerotoheroandroidtdd.note.create

import ru.easycode.zerotoheroandroidtdd.main.Screen

data class CreateNoteScreen(private val folderId: Long) :
    Screen.ReplaceBackstack(CreateNoteFragment::class.java)
