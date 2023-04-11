package com.gautam.domain.di

import com.gautam.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { AWeekWeatherUseCase(repository = get()) }
    factory { AHourWeatherUseCase(repository = get()) }
    factory { CurrentWeatherByCityUseCase(repository = get()) }
    factory { CurrentWeatherByLocationUseCase(repository = get()) }
}