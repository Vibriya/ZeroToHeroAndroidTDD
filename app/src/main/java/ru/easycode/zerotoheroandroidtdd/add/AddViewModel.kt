package ru.easycode.zerotoheroandroidtdd.add

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.main.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.AbstractViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : AbstractViewModel(clear, dispatcher) {

    fun add(value: String) {
        scope.launch {
            val id = async { repository.add(value) }
            withContext(dispatcherMain) {
                liveDataWrapper.add(ItemUi(id.await(), value))
            }
        }
    }
}
