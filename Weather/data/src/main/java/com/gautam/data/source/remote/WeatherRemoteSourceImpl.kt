package com.gautam.data.source.remote

import com.gautam.core.fundamentals.Constants
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.data.source.remote.api.CurrentWeatherApiService
import com.gautam.data.source.remote.mapper.WeatherMapper
import com.gautam.domain.model.CurrentWeatherModel
import com.google.android.gms.maps.model.LatLng


/**
 * Weather remote source impl
 *
 * @property currentWeatherApi
 * @property mapper
 * @constructor Create empty Weather remote source impl
 */
class WeatherRemoteSourceImpl(
    private val currentWeatherApi: CurrentWeatherApiService,
    private val mapper: WeatherMapper
) : WeatherRemoteSource {

    override suspend fun getCurrentWeatherByCity(city: String): Result<Error, CurrentWeatherModel> =
        execute(
            service = {
                currentWeatherApi.getCurrentWeatherByCity(city = city, appId = Constants.API_KEY)
            },
            success = { entity, _ ->
                mapper.toCurrentWeatherModel(entity)
            },
            failure = { code, msg ->
                Error.RemoteError(code,msg)
            }
        )

    override suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error, CurrentWeatherModel> =
        execute(
            service = {
                currentWeatherApi.getCurrentWeatherByLocation(
                    latitude = latLng.latitude,
                    longitude = latLng.longitude,
                    appId = Constants.API_KEY
                )
            },
            success = { entity, _ ->
                mapper.toCurrentWeatherModel(entity)
            },
            failure = {code, msg ->
                Error.RemoteError(code,msg)
            }
        )
}