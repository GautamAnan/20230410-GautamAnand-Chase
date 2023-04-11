package com.gautam.domain.usecase

import com.gautam.core.BaseUseCase
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.model.OneCallResponseModel
import com.gautam.domain.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import kotlin.coroutines.CoroutineContext

class AHourWeatherUseCase (private val repository: WeatherRepository) :
    BaseUseCase<CurrentHourWeatherParams, OneCallResponseModel> {
    override suspend fun execute(
        params: CurrentHourWeatherParams,
        coroutineContext: CoroutineContext
    ): Result<Error, OneCallResponseModel> = repository.getHourWeather(params.latLng)

}

data class CurrentHourWeatherParams(val latLng: LatLng)