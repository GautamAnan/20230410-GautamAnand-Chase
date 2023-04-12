package com.gautam.data.repository

import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.data.source.remote.WeatherRemoteSource
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng

/**
 * Weather repository impl
 *
 * @property source
 * @constructor Create empty Weather repository impl
 */
class WeatherRepositoryImpl(private val source: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun getCurrentWeatherByCity(city: String): Result<Error, CurrentWeatherModel> =
        source.getCurrentWeatherByCity(city)

    override suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error, CurrentWeatherModel> =
        source.getCurrentWeatherByLocation(latLng)


}