package com.gautam.data.source.remote

import com.gautam.core.fundamentals.ResponseMapper
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.domain.model.CurrentWeatherModel
import com.google.android.gms.maps.model.LatLng

/**
 * Weather remote source
 *
 * @constructor Create empty Weather remote source
 */
interface WeatherRemoteSource : ResponseMapper {
    /**
     * Get current weather by city
     *
     * @param city
     * @return
     */
    suspend fun getCurrentWeatherByCity(city: String): Result<Error, CurrentWeatherModel>

    /**
     * Get current weather by location
     *
     * @param latLng
     * @return
     */
    suspend fun getCurrentWeatherByLocation(latLng: LatLng): Result<Error, CurrentWeatherModel>


}


