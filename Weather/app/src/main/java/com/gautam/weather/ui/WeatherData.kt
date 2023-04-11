package com.gautam.weather.ui

import androidx.lifecycle.MutableLiveData
import com.gautam.core.BaseData
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.model.OneCallResponseModel

class WeatherData (
    val model: MutableLiveData<CurrentWeatherModel> = MutableLiveData()
) : BaseData()