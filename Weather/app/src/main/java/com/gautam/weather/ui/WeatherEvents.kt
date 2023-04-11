package com.gautam.weather.ui

import com.gautam.core.BaseEvent


sealed class WeatherEvents : BaseEvent {
    object ShowLoginScreen : WeatherEvents()


}