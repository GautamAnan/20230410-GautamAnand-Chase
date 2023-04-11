package com.gautam.domain.repository

import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.model.OneCallResponseModel
import com.google.android.gms.maps.model.LatLng
import com.gautam.core.fundamentals.Result
import com.gautam.core.fundamentals.Error

interface WeatherRepository {
    suspend fun getCurrentWeatherByCity(city: String): Result<Error,CurrentWeatherModel>

    suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error,CurrentWeatherModel>

    suspend fun getHourWeather(latLng: LatLng): Result<Error,OneCallResponseModel>

    suspend fun getSevenDaysWeather(latLng: LatLng): Result<Error,OneCallResponseModel>
}
