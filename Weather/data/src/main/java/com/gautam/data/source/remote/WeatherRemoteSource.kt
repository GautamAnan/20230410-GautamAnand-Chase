package com.gautam.data.source.remote

import com.gautam.core.fundamentals.ResponseMapper
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.model.OneCallResponseModel
import com.google.android.gms.maps.model.LatLng

interface WeatherRemoteSource : ResponseMapper {
    suspend fun getCurrentWeatherByCity(city: String): Result<Error, CurrentWeatherModel>

    suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error, CurrentWeatherModel>

    suspend fun getHourWeather(latLng: LatLng): Result<Error, OneCallResponseModel>

    suspend fun getSevenDaysWeather(latLng: LatLng): Result<Error, OneCallResponseModel>
}


