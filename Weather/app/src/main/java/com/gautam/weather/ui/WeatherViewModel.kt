package com.gautam.weather.ui

import android.app.Application
import com.gautam.core.BaseViewModel


class WeatherViewModel(
    application: Application,
    data: WeatherData
) : BaseViewModel<WeatherData, WeatherEvents>(application, data) {


}