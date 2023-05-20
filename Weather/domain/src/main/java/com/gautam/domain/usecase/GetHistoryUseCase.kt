package com.gautam.domain.usecase


import com.gautam.core.BaseUseCase
import com.gautam.domain.repository.HistoryWeatherRepository
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import kotlin.coroutines.CoroutineContext

class GetHistoryUseCase (private val repository: HistoryWeatherRepository) :
    BaseUseCase<HistorySearchParams, HistorySearchResult> {
    override suspend fun execute(
        params: HistorySearchParams,
        coroutineContext: CoroutineContext
    ): Result<Error, HistorySearchResult> {
        return repository.getHistoryList(HistorySearchParams(params.searchItem))
    }
}

class HistorySearchResult(val historyList: List<String>)

data  class HistorySearchParams(val searchItem:String)
