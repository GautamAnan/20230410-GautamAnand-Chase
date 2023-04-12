package com.gautam.domain.repository

import com.gautam.domain.model.CurrentWeatherModel
import com.google.android.gms.maps.model.LatLng
import com.gautam.core.fundamentals.Result
import com.gautam.core.fundamentals.Error

/**
 * Weather repository
 *
 * @constructor Create empty Weather repository
 */
interface WeatherRepository {
    /**
     * Get current weather by city
     *
     * @param city
     * @return
     */
    suspend fun getCurrentWeatherByCity(city: String): Result<Error,CurrentWeatherModel>

    /**
     * Get current weather by location
     *
     * @param latLng
     * @return
     */
    suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error,CurrentWeatherModel>

}
