package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun init(firstOpening: Boolean) {
        if (firstOpening) liveDataWrapper.update(UiState.Initial)
    }
    fun load(){
        liveDataWrapper.update(UiState.ShowProgress)
        scope.launch {
            repository.load()
            liveDataWrapper.update(UiState.ShowData)
        }
    }

    fun liveData() = liveDataWrapper.liveData()
}