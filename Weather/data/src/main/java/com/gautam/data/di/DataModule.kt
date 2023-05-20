package com.gautam.data.di

import com.gautam.core.fundamentals.Constants.CLIENT_MODULE
import com.gautam.data.db.RealmConfig
import com.gautam.data.repository.HistoryRepositoryImpl
import com.gautam.data.repository.WeatherRepositoryImpl
import com.gautam.data.source.db.HistoryListRemoteSource
import com.gautam.data.source.db.HistoryListRemoteSourceImpl
import com.gautam.data.source.db.mapper.DbMapper
import com.gautam.data.source.remote.WeatherRemoteSource
import com.gautam.data.source.remote.WeatherRemoteSourceImpl
import com.gautam.data.source.remote.api.CurrentWeatherApiService
import com.gautam.data.source.remote.mapper.WeatherMapper
import com.gautam.domain.repository.HistoryWeatherRepository
import com.gautam.domain.repository.WeatherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module {
    single<CurrentWeatherApiService> {
        get<Retrofit>(named(CLIENT_MODULE)).create(
            CurrentWeatherApiService::class.java
        )
    }

}

val repositoryModule = module {
    single<WeatherRepository> {  WeatherRepositoryImpl(source = get()) }
    single<HistoryWeatherRepository> {  HistoryRepositoryImpl(source = get()) }
}

val sourceModule = module {
    single<WeatherRemoteSource> {
        WeatherRemoteSourceImpl(
            currentWeatherApi = get(),
            mapper = get()
        )
    }
    single<HistoryListRemoteSource> {
        HistoryListRemoteSourceImpl(
            config = get(),
            dbMapper = get()
        )
    }

}

val mapperModule = module {
    single { WeatherMapper() }
    single { DbMapper() }
}
val databaseModule = module {
    single {
        RealmConfig.build()
    }
}