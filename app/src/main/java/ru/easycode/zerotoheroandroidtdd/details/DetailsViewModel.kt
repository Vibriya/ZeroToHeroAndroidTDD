package ru.easycode.zerotoheroandroidtdd.details

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.main.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.AbstractViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Repository

class DetailsViewModel(
    private val changeLiveDataWrapper: ListLiveDataWrapper.All,
    private val repository: Repository.Change,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : AbstractViewModel(clear, dispatcher) {

    val liveData: MutableLiveData<String> = MutableLiveData()
    private lateinit var itemUi: ItemUi

    fun init(itemId: Long) {
        scope.launch {
            val deferred = async {
                repository.item(itemId)
            }
            val item = deferred.await()
            itemUi = ItemUi(item.id, item.text)
            withContext(dispatcherMain){
                liveData.value = itemUi.text
            }
        }
    }

    fun delete(itemId: Long) {
        scope.launch {
            repository.delete(itemId)
            withContext(dispatcherMain){
                changeLiveDataWrapper.delete(itemUi)
            }
            comeback()
        }
    }

    fun update(itemId: Long, newText: String) {
        scope.launch {
            repository.update(itemId, newText)
            withContext(dispatcherMain) {
                changeLiveDataWrapper.update(ItemUi(itemId, newText))
                liveData.value = newText
            }
            comeback()
        }
    }

}
