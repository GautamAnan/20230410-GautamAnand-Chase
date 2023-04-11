package com.gautam.data.di

import com.gautam.data.repository.WeatherRepositoryImpl
import com.gautam.data.source.remote.WeatherRemoteSource
import com.gautam.data.source.remote.WeatherRemoteSourceImpl
import com.gautam.data.source.remote.api.CurrentWeatherApiService
import com.gautam.data.source.remote.api.OneCallApiService
import com.gautam.data.source.remote.mapper.WeatherMapper
import com.gautam.domain.repository.WeatherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module {
    single<CurrentWeatherApiService> {
        get<Retrofit>().create(
            CurrentWeatherApiService::class.java
        )
    }
    single<OneCallApiService> {
        get<Retrofit>().create(
            OneCallApiService::class.java
        )
    }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(source = get()) }
}

val sourceModule = module {
    single<WeatherRemoteSource> {
        WeatherRemoteSourceImpl(
            apiOneCall = get(),
            currentWeatherApi = get(),
            mapper = get()
        )
    }

}

val mapperModule = module {
    single { WeatherMapper() }
}
