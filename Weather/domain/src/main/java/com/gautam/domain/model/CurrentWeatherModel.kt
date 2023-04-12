package com.gautam.domain.model

data class CurrentWeatherModel(
    var id: Int? = 0,
    var name: String? = "",
    var cod: Int? = 0,
    var weatherModelItems: List<WeatherModel>? = emptyList(),
    var base: String? = "",
    var mainModel: MainModel? = null,
    var visibility: Int? = 0,
    var windModel: WindModel? = null,
    var clouds: CloudModel? = null,
    var dt: Long? = 0L,
    var sysModel: SysModel? = null,
    var timezone: Int? = null
)
