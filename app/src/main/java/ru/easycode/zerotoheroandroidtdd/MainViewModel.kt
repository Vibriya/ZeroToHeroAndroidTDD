package ru.easycode.zerotoheroandroidtdd

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) : ViewModel() {
    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.load()
            withContext(Dispatchers.Main.immediate) {
                liveDataWrapper.update(UiState.ShowData(response.text))
            }
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    @MainThread
    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }

    fun liveData() : LiveData<UiState> = liveDataWrapper.liveData()

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.google.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                MainViewModel(LiveDataWrapper.Base(), Repository.Base(
                    retrofit.create(SimpleService::class.java), URL)
                )
            }
        }
        private const val URL =
            "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}
