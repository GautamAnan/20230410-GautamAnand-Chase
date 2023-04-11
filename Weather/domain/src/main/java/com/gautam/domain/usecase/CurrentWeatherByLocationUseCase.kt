package com.gautam.domain.usecase

import com.gautam.core.BaseUseCase
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import kotlin.coroutines.CoroutineContext

class CurrentWeatherByLocationUseCase (private val repository: WeatherRepository) :
    BaseUseCase<CurrentWeatherByLocationParams, CurrentWeatherModel> {
    override suspend fun execute(
        params: CurrentWeatherByLocationParams,
        coroutineContext: CoroutineContext
    ): Result<Error, CurrentWeatherModel> = repository.getCurrentWeatherByLocation(params.latLng)

}

data class CurrentWeatherByLocationParams(val latLng: LatLng)
