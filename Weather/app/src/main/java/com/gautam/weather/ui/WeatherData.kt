package com.gautam.weather.ui

import androidx.lifecycle.MutableLiveData
import com.gautam.core.BaseData
import com.gautam.domain.model.CurrentWeatherModel

class WeatherData (
    val model: MutableLiveData<CurrentWeatherModel> = MutableLiveData(),
    val locationSearchTexts: MutableSet<String> = mutableSetOf()
) : BaseData()