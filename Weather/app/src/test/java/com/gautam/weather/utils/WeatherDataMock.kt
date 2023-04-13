package com.gautam.weather.utils

import com.gautam.domain.model.*

val resultListMock = CurrentWeatherModel(
    weatherModelItems = listOf<WeatherModel>(WeatherModel(803, "Clouds", "broken clouds", "04n")),
    base = "stations",
    mainModel = MainModel(279.64, 278.54, 279.23, 280.49, 1023.0, 64, 1023.0, 934.0),
    visibility = 10000,
    windModel = WindModel(1.72, 212.0, 1.58),
    clouds = CloudModel(66),
    dt = 1681153590,
    sysModel = SysModel(2, 2004688, "IT", "15 Apr 2023 11:20 AM", "15 Apr 2023 01:20 AM"),
    timezone = 7200,
    id = 3163858,
    name = "Zocca",
    cod = 200
)
