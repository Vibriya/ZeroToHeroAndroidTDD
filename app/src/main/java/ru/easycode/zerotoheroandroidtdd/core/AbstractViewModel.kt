package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

abstract class AbstractViewModel(
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    protected val scope = CoroutineScope(dispatcher)

    fun comeback() {
        clear.clearViewModel(this::class.java)
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
        comeback()
    }
}