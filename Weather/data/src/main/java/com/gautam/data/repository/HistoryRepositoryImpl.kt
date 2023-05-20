package com.gautam.data.repository

import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.data.source.db.HistoryListRemoteSource
import com.gautam.domain.repository.HistoryWeatherRepository
import com.gautam.domain.usecase.HistoryResult
import com.gautam.domain.usecase.HistorySearchParams
import com.gautam.domain.usecase.HistorySearchResult

class HistoryRepositoryImpl (private val source: HistoryListRemoteSource) :
    HistoryWeatherRepository {

    override suspend fun getHistoryList(params: HistorySearchParams): Result<Error, HistorySearchResult> =
         source.getHistoryList(params)


    override suspend fun setHistoryItem(historyItem: String): Result<Error, HistoryResult> =
        source.setHistoryItem(historyItem)

}