package com.gautam.data.db

import com.gautam.core.fundamentals.Constants.DB_NAME
import io.realm.RealmConfiguration


object RealmConfig {
    // Creating our db with custom properties
    fun build(): RealmConfiguration = RealmConfiguration.Builder()
        .name(DB_NAME)
        .allowQueriesOnUiThread(false)
        .schemaVersion(1)
        .deleteRealmIfMigrationNeeded()
        .build()
}