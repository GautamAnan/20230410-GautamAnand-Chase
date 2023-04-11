package com.gautam.domain.model

data class DailyModel(
    val dt: Long? = 0L,
    val sunrise: Long? = 0L,
    val sunset: Long? = 0L,
    val moonrise: Long? = 0L,
    val moonset: Long? = 0L,
    val moonPhase: Double? = 0.0,
    val tempModel: TempModel? = null,
    val feelsLike: FeelLikeModel? = null,
    val pressure: Int? = 0,
    val humidity: Int? = 0,
    val dewPoint: Double? = 0.0,
    val windSpeed: Double? = 0.0,
    val windDeg: Double? = 0.0,
    val windGust: Double? = 0.0,
    val weatherModel: List<WeatherModel>? = emptyList(),
    val clouds: Int? = 0,
    val pop: Double? = 0.0,
    val uvi: Double? = 0.0,
)
