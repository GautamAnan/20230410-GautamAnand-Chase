package com.gautam.weather

import android.app.Application
import com.gautam.data.di.apiModule
import com.gautam.data.di.databaseModule
import com.gautam.data.di.mapperModule
import com.gautam.data.di.repositoryModule
import com.gautam.data.di.sourceModule
import com.gautam.domain.di.useCaseModule
import com.gautam.weather.di.networkModule
import com.gautam.weather.di.presentationModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(
                listOf(
                    apiModule,
                    networkModule,
                    useCaseModule,
                    repositoryModule,
                    sourceModule,
                    mapperModule,
                    presentationModule,
                    databaseModule
                )
            )
        }
        Realm.init(this)
    }
}