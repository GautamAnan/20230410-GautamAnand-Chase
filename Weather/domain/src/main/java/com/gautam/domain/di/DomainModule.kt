package com.gautam.domain.di

import com.gautam.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { CurrentWeatherByCityUseCase(repository = get()) }
    factory { CurrentWeatherByLocationUseCase(repository = get()) }
    factory { GetHistoryUseCase(repository = get()) }
    factory { SetHistoryUseCase(repository = get()) }
}