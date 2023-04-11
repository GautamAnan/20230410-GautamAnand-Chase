package com.gautam.data.source.remote.api

import com.gautam.data.entity.OneCallResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OneCallApiService {
    @GET("onecall")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "current,daily,alerts,minutes",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String
    ): Response<OneCallResponse>

    @GET("onecall")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getSevenDaysWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "current,alerts,minutes,hourly",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String
    ): Response<OneCallResponse>
}