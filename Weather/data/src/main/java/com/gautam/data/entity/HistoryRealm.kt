package com.gautam.data.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class HistoryRealm: RealmObject() {
    @PrimaryKey
    var id: String = ObjectId().toHexString()
    var historyCase: String = ""
}