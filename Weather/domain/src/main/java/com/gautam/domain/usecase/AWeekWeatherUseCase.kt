package com.gautam.domain.usecase

import com.gautam.core.BaseUseCase
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.model.OneCallResponseModel
import com.gautam.domain.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import kotlin.coroutines.CoroutineContext

class AWeekWeatherUseCase (private val repository: WeatherRepository) :
    BaseUseCase<CurrentWeekWeatherParams, OneCallResponseModel> {
    override suspend fun execute(
        params: CurrentWeekWeatherParams,
        coroutineContext: CoroutineContext
    ): Result<Error, OneCallResponseModel> = repository.getSevenDaysWeather(params.latLng)

}

data class CurrentWeekWeatherParams(val latLng: LatLng)