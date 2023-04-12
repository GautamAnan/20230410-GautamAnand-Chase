package com.gautam.weather.ui

import com.gautam.core.BaseEvent
import com.gautam.domain.model.CurrentWeatherModel

sealed class WeatherEvents : BaseEvent {
    object ChangeLocation : WeatherEvents()
    object CallHomeScreen : WeatherEvents()

}