package com.gautam.data.source.db.mapper

import com.gautam.data.entity.HistoryRealm
import io.realm.RealmResults

class DbMapper {
    fun toModel(list: RealmResults<HistoryRealm>): List<String> {
        return list.map {
            it.historyCase
        }
    }

    fun toHistoryItemRealm(historyItem: String): HistoryRealm {
        return HistoryRealm().apply {
            this.historyCase = historyItem
        }
    }
}