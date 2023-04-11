package com.gautam.domain.usecase

import com.gautam.core.BaseUseCase
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.repository.WeatherRepository
import kotlin.coroutines.CoroutineContext
import com.gautam.core.fundamentals.Result
import com.gautam.core.fundamentals.Error

class CurrentWeatherByCityUseCase(private val repository: WeatherRepository) :
    BaseUseCase<CurrentWeatherParams, CurrentWeatherModel> {
    override suspend fun execute(
        params: CurrentWeatherParams,
        coroutineContext: CoroutineContext
    ): Result<Error, CurrentWeatherModel> = repository.getCurrentWeatherByCity(params.city)

}

data class CurrentWeatherParams(val city: String)