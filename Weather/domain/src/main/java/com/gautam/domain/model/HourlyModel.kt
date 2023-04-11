package com.gautam.domain.model

data class HourlyModel(
    val dt: Long? = 0L,
    val temp: Double? = 0.0,
    val feelsLike: Double? = 0.0,
    val pressure: Double? = 0.0,
    val humidity: Int? = 0,
    val dewPoint: Double? = 0.0,
    val uvi: Double? = 0.0,
    val clouds: Double? = 0.0,
    val visibility: Int? = 0,
    val windSpeed: Double? = 0.0,
    val windDeg: Int? = 0,
    val windGust: Double? = 0.0,
    val weatherModel: List<WeatherModel>? = emptyList(),
    val pop: Double? = 0.0,
)
