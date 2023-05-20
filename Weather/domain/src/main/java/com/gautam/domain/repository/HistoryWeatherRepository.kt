package com.gautam.domain.repository


import com.gautam.domain.usecase.HistoryParams
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.usecase.HistoryResult
import com.gautam.domain.usecase.HistorySearchParams
import com.gautam.domain.usecase.HistorySearchResult

interface HistoryWeatherRepository {

    suspend fun getHistoryList(params: HistorySearchParams): Result<Error, HistorySearchResult>

    suspend fun setHistoryItem(historyItem:String): Result<Error, HistoryResult>
}