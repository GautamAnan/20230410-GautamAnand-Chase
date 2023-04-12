package com.gautam.data.source.remote.api


import com.gautam.data.entity.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Current weather api service
 *
 * @constructor Create empty Current weather api service
 */
interface CurrentWeatherApiService {
    /**
     * Get current weather by city
     *
     * @param city
     * @param units
     * @param lang
     * @param appId
     * @return
     */
    @GET("weather")
    suspend fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String
    ): Response<CurrentWeather>

    /**
     * Get current weather by location
     *
     * @param latitude
     * @param longitude
     * @param units
     * @param lang
     * @param appId
     * @return
     */
    @GET("weather")
    suspend fun getCurrentWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String,
    ): Response<CurrentWeather>
}
