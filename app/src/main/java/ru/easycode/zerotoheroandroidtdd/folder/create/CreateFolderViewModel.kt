package ru.easycode.zerotoheroandroidtdd.folder.create

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.ComebackViewModel
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class CreateFolderViewModel(
    private val repository: FoldersRepository.Create,
    private val liveDataWrapper: FolderListLiveDataWrapper.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ComebackViewModel(dispatcher, clear) {

    fun createFolder(name: String) {
        scope.launch {
            val folderId = repository.createFolder(name)
            withContext(dispatcherMain){
                liveDataWrapper.create(FolderUi(folderId, name, 0))
                comeback()
            }
        }
    }

    override fun comeback() {
        super.comeback()
        navigation.update(FoldersListScreen)
    }
}
