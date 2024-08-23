package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.create.CreateFolderScreen
import ru.easycode.zerotoheroandroidtdd.folder.create.CreateFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.edit.EditFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteViewModel
import ru.easycode.zerotoheroandroidtdd.note.edit.EditNoteViewModel
import ru.easycode.zerotoheroandroidtdd.note.edit.NoteLiveDataWrapper

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clasz: Class<T>): T

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val provide: ProvideViewModel
    ) : ProvideViewModel, ClearViewModels {
        private val cache: MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            return if (cache.containsKey(clasz))
                cache[clasz] as T
            else {
                val viewModel = provide.viewModel(clasz)
                cache[clasz] = viewModel
                viewModel
            }
        }

        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelClasses.forEach {
                cache.remove(it)
            }
        }

    }

    class Base(
        private val context: Context,
        private val clear: ClearViewModels
    ) : ProvideViewModel {
        private val dataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, "AppDataBase").build()
        private val foldersRepository =
            FoldersRepository.Base(Now.Base, dataBase.foldersDao(), dataBase.notesDao())
        private val notesRepository =
            NotesRepository.Base(Now.Base, dataBase.notesDao())

        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            return when (clasz) {
                MainViewModel::class.java -> MainViewModel(Navigation.Base)

                FolderListViewModel::class.java -> FolderListViewModel(
                    foldersRepository,
                    FolderListLiveDataWrapper.Base,
                    FolderLiveDataWrapper.Base,
                    Navigation.Base,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                FolderDetailsViewModel::class.java -> FolderDetailsViewModel(
                    notesRepository,
                    NoteListLiveDataWrapper.Base,
                    FolderLiveDataWrapper.Base,
                    Navigation.Base,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                CreateFolderViewModel::class.java -> CreateFolderViewModel(
                    foldersRepository,
                    FolderListLiveDataWrapper.Base,
                    Navigation.Base,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                EditFolderViewModel::class.java -> EditFolderViewModel(
                    FolderLiveDataWrapper.Base,
                    foldersRepository,
                    Navigation.Base,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                CreateNoteViewModel::class.java -> CreateNoteViewModel(
                    FolderLiveDataWrapper.Base,
                    NoteListLiveDataWrapper.Base,
                    notesRepository,
                    Navigation.Base,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                EditNoteViewModel::class.java -> EditNoteViewModel(
                    FolderLiveDataWrapper.Base,
                    NoteLiveDataWrapper.Base,
                    NoteListLiveDataWrapper.Base,
                    notesRepository,
                    Navigation.Base,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                else -> throw IllegalArgumentException("ViewModel ${clasz.simpleName} does not exist")
            } as T
        }
    }
}
