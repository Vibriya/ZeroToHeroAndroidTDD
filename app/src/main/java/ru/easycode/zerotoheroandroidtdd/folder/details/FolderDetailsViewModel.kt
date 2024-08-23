package ru.easycode.zerotoheroandroidtdd.folder.details

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.ComebackViewModel
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.edit.EditFolderScreen
import ru.easycode.zerotoheroandroidtdd.folder.list.Folder
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.note.core.MyNote
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteScreen
import ru.easycode.zerotoheroandroidtdd.note.edit.EditNoteScreen

class FolderDetailsViewModel(
    private val noteListRepository: NotesRepository.ReadList,
    private val liveDataWrapper: NoteListLiveDataWrapper.UpdateListAndRead,
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ComebackViewModel(dispatcher, clear) {

    fun init() {
        scope.launch {
            val notes: List<MyNote> = noteListRepository.noteList(folderLiveDataWrapper.folderId())
            withContext(dispatcherMain) {
                liveDataWrapper.update(notes.map { NoteUi(it.id, it.title, it.folderId) })
            }
        }
    }

    fun createNote() {
        navigation.update(CreateNoteScreen(folderLiveDataWrapper.folderId()))
    }

    fun editNote(noteUi: NoteUi) {
        navigation.update(EditNoteScreen(noteUi.id))
    }

    fun editFolder() {
        navigation.update(EditFolderScreen(folderLiveDataWrapper.folderId()))
    }

    override fun comeback() {
        super.comeback()
        navigation.update(FoldersListScreen)
    }
}
