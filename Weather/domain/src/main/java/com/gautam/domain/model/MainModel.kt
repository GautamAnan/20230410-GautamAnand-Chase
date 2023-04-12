package com.gautam.domain.model

data class MainModel(
    var temp: Double? = 0.0,
    var feelsLike: Double? = 0.0,
    var tempMin: Double? = 0.0,
    var tempMax: Double? = 0.0,
    var pressure: Double? = 0.0,
    var humidity: Int? = 0,
    var seaLevel: Double? = 0.0,
    var grndLevel: Double? = 0.0,
)
