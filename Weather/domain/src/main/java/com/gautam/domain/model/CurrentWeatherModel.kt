package com.gautam.domain.model

data class CurrentWeatherModel(
    val id: Int? = 0,
    val name: String? = "",
    val cod: Int? = 0,
    val weatherModelItems: List<WeatherModel>? = emptyList(),
    val base: String? = "",
    val mainModel: MainModel? = null,
    val visibility: Int? = 0,
    val windModel: WindModel? = null,
    val clouds: CloudModel? = null,
    val dt: Long? = 0L,
    val sysModel: SysModel? = null,
    val timezone: Int? = null
)
