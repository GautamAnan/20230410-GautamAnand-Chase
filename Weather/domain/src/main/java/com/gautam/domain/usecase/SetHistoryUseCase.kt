package com.gautam.domain.usecase

import com.gautam.core.BaseUseCase
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.repository.HistoryWeatherRepository
import kotlin.coroutines.CoroutineContext

class SetHistoryUseCase(private val repository: HistoryWeatherRepository) :
    BaseUseCase<HistoryParams, HistoryResult> {
    override suspend fun execute(
        params: HistoryParams,
        coroutineContext: CoroutineContext
    ): Result<Error, HistoryResult> {
        return repository.setHistoryItem(params.historyItem)
    }
}

class HistoryResult

data  class HistoryParams(val historyItem:String)