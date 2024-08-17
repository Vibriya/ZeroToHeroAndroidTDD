package ru.easycode.zerotoheroandroidtdd.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    private val scope = CoroutineScope(dispatcher)

    fun add(value: String) {
        scope.launch {
            repository.add(value)
            withContext(dispatcherMain) {
                liveDataWrapper.add(value)
            }
        }
        comeback()
    }

    fun comeback() {
        clear.clearViewModel(this::class.java)
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
        comeback()
    }
}
