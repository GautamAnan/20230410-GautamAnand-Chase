package com.gautam.domain.model

data class CurrentModel(
    val dt: Long? = 0L,
    val sunrise: Long? = 0L,
    val sunset: Long? = 0L,
    val temp: Double? = 0.0,
    val feelsLike: Double? = 0.0,
    val pressure: Int? = 0,
    val humidity: Int? = 0,
    val dewPoint: Double? = 0.0,
    val uvi: Double? = 0.0,
    val clouds: Int? = 0,
    val visibility: Int? = 0,
    val windSpeed: Int? = 0,
    val windDeg: Int? = 0,
    val weatherModel: List<WeatherModel>? = emptyList(),
)
