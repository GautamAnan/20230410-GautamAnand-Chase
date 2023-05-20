package com.gautam.data.source.db


import com.gautam.core.fundamentals.ResponseMapper
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.usecase.HistoryResult
import com.gautam.domain.usecase.HistorySearchParams
import com.gautam.domain.usecase.HistorySearchResult

interface HistoryListRemoteSource: ResponseMapper {
    suspend fun getHistoryList(params: HistorySearchParams): Result<Error, HistorySearchResult>

    suspend fun setHistoryItem(historyItem: String): Result<Error, HistoryResult>
}