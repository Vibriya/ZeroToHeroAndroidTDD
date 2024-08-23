package ru.easycode.zerotoheroandroidtdd.folder.edit

import ru.easycode.zerotoheroandroidtdd.main.Screen

data class EditFolderScreen(private val folderId: Long) :
    Screen.ReplaceBackstack(EditFolderFragment::class.java)
