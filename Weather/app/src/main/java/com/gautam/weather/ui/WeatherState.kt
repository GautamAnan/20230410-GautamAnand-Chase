package com.gautam.weather.ui

import androidx.compose.runtime.Stable
import com.gautam.domain.model.CurrentWeatherModel

sealed class WeatherState {

    object Loading : WeatherState()
    object NoState : WeatherState()
    data class Response(val result: CurrentWeatherModel) : WeatherState()
    data class Error(val error: String) : WeatherState()

}