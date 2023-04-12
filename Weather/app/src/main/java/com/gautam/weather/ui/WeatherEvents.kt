package com.gautam.weather.ui

import com.gautam.core.BaseEvent
import com.gautam.domain.model.CurrentWeatherModel

sealed class WeatherEvents : BaseEvent {
    object ChangeLocation : WeatherEvents()
    object CallHomeScreen : WeatherEvents()
    object NetworkError : WeatherEvents()
    data class CallError(val errorMsg: String) : WeatherEvents()

}