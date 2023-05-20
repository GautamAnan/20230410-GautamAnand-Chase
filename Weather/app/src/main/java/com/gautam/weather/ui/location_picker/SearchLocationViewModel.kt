package com.gautam.weather.ui.location_picker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gautam.core.fundamentals.Error
import com.gautam.domain.usecase.GetHistoryUseCase
import com.gautam.domain.usecase.HistorySearchParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchLocationViewModel(private val historyUseCase: GetHistoryUseCase) : ViewModel() {

    val topics: MutableLiveData<LocationState> = MutableLiveData(LocationState.NoState)

    fun fetchTopics(searchTerm: String) {
        viewModelScope.launch (Dispatchers.IO){
            topics.postValue(LocationState.Response(emptyList()))
            //topics.value = LocationState.Loading
            historyUseCase.execute(HistorySearchParams(searchTerm)).mapResult(
                success = {
                    topics.postValue(LocationState.Response(it.historyList))
                },
                failure = {
                    when (it) {
                        is Error.RemoteError -> topics.postValue(LocationState.Error("${it.code} - ${it.message}"))
                        is Error.GenericError -> topics.postValue(LocationState.Error("${it.message} "))
                        else -> {}
                    }
                }
            )
            /*
                  topics.filter { it.contains(searchTerm, ignoreCase = true) }
              */
        }

    }
}