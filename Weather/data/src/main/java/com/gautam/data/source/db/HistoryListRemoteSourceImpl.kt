package com.gautam.data.source.db

import android.util.Log
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.data.entity.HistoryRealm
import com.gautam.data.source.db.mapper.DbMapper
import com.gautam.domain.usecase.HistoryResult
import com.gautam.domain.usecase.HistorySearchParams
import com.gautam.domain.usecase.HistorySearchResult
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers

class HistoryListRemoteSourceImpl(
    private val config: RealmConfiguration,
    private val dbMapper: DbMapper
) : HistoryListRemoteSource {

    override suspend fun getHistoryList(params: HistorySearchParams): Result<Error, HistorySearchResult> {
        val parking = mutableListOf<String>()
        val realm = Realm.getInstance(config)
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            parking.addAll(
                dbMapper.toModel(
                    realmTransaction
                        .where(HistoryRealm::class.java).run {
                            if (params.searchItem.isNotEmpty())
                                contains("historyCase", params.searchItem)

                            findAll()
                        }


                )
            )
        }
        return Result.Success(HistorySearchResult(parking))
    }

    override suspend fun setHistoryItem(historyItem: String): Result<Error, HistoryResult> {
        Log.d("setHistoryItem", "Initial ")
        val realm = Realm.getInstance(config)
        try {
            realm.executeTransaction { realmTransaction ->
                realmTransaction.insert(dbMapper.toHistoryItemRealm(historyItem))
            }
        } catch (exception: Exception) {
            Log.d("setHistoryItem", exception.message.toString())
        }

        return Result.Success(HistoryResult())
    }

}