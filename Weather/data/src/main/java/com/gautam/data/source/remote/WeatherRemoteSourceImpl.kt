package com.gautam.data.source.remote

import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.data.source.remote.api.CurrentWeatherApiService
import com.gautam.data.source.remote.api.OneCallApiService
import com.gautam.data.source.remote.mapper.WeatherMapper
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.model.OneCallResponseModel
import com.google.android.gms.maps.model.LatLng


class WeatherRemoteSourceImpl(
    private val apiOneCall: OneCallApiService,
    private val currentWeatherApi: CurrentWeatherApiService,
    private val mapper: WeatherMapper
) : WeatherRemoteSource {

    override suspend fun getCurrentWeatherByCity(city: String): Result<Error, CurrentWeatherModel> =
        execute(
            service = {
                currentWeatherApi.getCurrentWeatherByCity(city = city, appId = "")
            },
            success = { entity, _ ->
                mapper.toCurrentWeatherModel(entity)
            },
            failure = {
                Error.RemoteError()
            }
        )

    override suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error, CurrentWeatherModel> =
        execute(
            service = {
                currentWeatherApi.getCurrentWeatherByLocation(
                    latitude = latLng.latitude,
                    longitude = latLng.longitude,
                    appId = ""
                )
            },
            success = { entity, _ ->
                mapper.toCurrentWeatherModel(entity)
            },
            failure = {
                Error.RemoteError()
            }
        )

    override suspend fun getHourWeather(latLng: LatLng): Result<Error, OneCallResponseModel> =
        execute(
            service = {
                apiOneCall.getWeather(lon = latLng.longitude, lat = latLng.latitude, appId = "")
            },
            success = { entity, _ ->
                mapper.toOneCallResponseModel(entity)
            },
            failure = {
                Error.RemoteError()
            }
        )

    override suspend fun getSevenDaysWeather(latLng: LatLng): Result<Error, OneCallResponseModel> =
        execute(
            service = {
                apiOneCall.getSevenDaysWeather(lon = latLng.longitude, lat = latLng.latitude, appId = "")
            },
            success = { entity, _ ->
                mapper.toOneCallResponseModel(entity)
            },
            failure = {
                Error.RemoteError()
            }
        )
}