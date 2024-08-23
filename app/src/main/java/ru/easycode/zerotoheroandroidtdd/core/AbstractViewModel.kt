package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

abstract class AbstractViewModel(
    dispatcher: CoroutineDispatcher
) : ViewModel() {
    protected val scope = CoroutineScope(dispatcher)


    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}

abstract class ComebackViewModel(
    dispatcher: CoroutineDispatcher,
    private val clear: ClearViewModels
) : AbstractViewModel(dispatcher) {

    open fun comeback() {
        clear.clear(this::class.java)
    }

    override fun onCleared() {
        super.onCleared()
        comeback()
    }
}