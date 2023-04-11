package com.gautam.weather.ui

import com.gautam.core.BaseActivity
import com.gautam.core.BaseEvent
import com.gautam.weather.R
import com.gautam.weather.databinding.ActivityWeatherBinding


class WeatherActivity :
    BaseActivity<ActivityWeatherBinding, WeatherEvents, WeatherData, WeatherViewModel>(
        WeatherViewModel::class,
        R.layout.activity_weather
    ) {
    override fun eventUpdated(event: BaseEvent) {
        TODO("Not yet implemented")
    }


}