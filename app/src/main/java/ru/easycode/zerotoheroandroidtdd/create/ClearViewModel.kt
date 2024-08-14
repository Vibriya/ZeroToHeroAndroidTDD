package ru.easycode.zerotoheroandroidtdd.create

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out ViewModel>)
}
