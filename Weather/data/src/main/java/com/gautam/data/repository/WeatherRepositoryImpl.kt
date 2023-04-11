package com.gautam.data.repository

import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.data.source.remote.WeatherRemoteSource
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.model.OneCallResponseModel
import com.gautam.domain.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng

class WeatherRepositoryImpl(private val source: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun getCurrentWeatherByCity(city: String): Result<Error, CurrentWeatherModel> =
        source.getCurrentWeatherByCity(city)

    override suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error, CurrentWeatherModel> =
        source.getCurrentWeatherByLocation(latLng)

    override suspend fun getHourWeather(latLng: LatLng): Result<Error, OneCallResponseModel> =
        source.getHourWeather(latLng)

    override suspend fun getSevenDaysWeather(latLng: LatLng): Result<Error, OneCallResponseModel> =
        source.getSevenDaysWeather(latLng)

}