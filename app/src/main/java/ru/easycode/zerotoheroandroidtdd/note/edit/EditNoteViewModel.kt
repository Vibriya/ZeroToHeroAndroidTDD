package ru.easycode.zerotoheroandroidtdd.note.edit

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.AbstractViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.ComebackViewModel
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository

class EditNoteViewModel(
    private val folderLiveDataWrapper:  FolderLiveDataWrapper.Decrement,
    private val noteLiveDataWrapper: NoteLiveDataWrapper,
    private val noteListLiveDataWrapper: NoteListLiveDataWrapper.Update,
    private val repository: NotesRepository.Edit,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ComebackViewModel(dispatcher, clear) {

    fun init(noteId: Long) {
        scope.launch {
            val note = repository.note(noteId)
            withContext(dispatcherMain) {
                noteLiveDataWrapper.update(note.title)
            }
        }
    }

    fun deleteNote(noteId: Long) {
        scope.launch {
            repository.deleteNote(noteId)
            withContext(dispatcherMain) {
                folderLiveDataWrapper.decrement()
                comeback()
            }
        }
    }

    fun renameNote(noteId: Long, newText: String) {
        scope.launch {
            repository.renameNote(noteId, newText)
            withContext(dispatcherMain) {
                noteListLiveDataWrapper.update(noteId, newText)
                comeback()
            }
        }
    }

    override fun comeback() {
        super.comeback()
        navigation.update(FolderDetailsScreen)
    }
}
