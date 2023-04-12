package com.gautam.weather.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gautam.core.BaseViewModel
import com.gautam.core.SharedBaseViewModel
import com.gautam.domain.usecase.CurrentWeatherByCityUseCase
import com.gautam.domain.usecase.CurrentWeatherByLocationParams
import com.gautam.domain.usecase.CurrentWeatherByLocationUseCase
import com.gautam.domain.usecase.CurrentWeatherParams
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class WeatherViewModel(
    application: Application,
    data: WeatherData,
    private val weatherUseCase: CurrentWeatherByCityUseCase,
    private val weatherByLocationUseCase: CurrentWeatherByLocationUseCase
) : SharedBaseViewModel<WeatherData, WeatherEvents>(application, data) {

     fun getWeatherByName(location :String ="Pune") {
        viewModelScope.launch {
            data.loading()
            weatherUseCase.execute(CurrentWeatherParams(location)).mapResult(
                {
                    data.hideLoading()
                    data.model.postValue(it)
                    updateEvent(WeatherEvents.CallHomeScreen)
                }, {
                    it.toString()
                    data.error()
                }
            )
        }
    }

     fun getWeatherByLocation(latLng: LatLng) {
        viewModelScope.launch {
            data.loading()
            weatherByLocationUseCase.execute(CurrentWeatherByLocationParams(latLng)).mapResult(
                {
                    data.hideLoading()
                    data.model.postValue(it)
                }, {
                    data.error()
                }
            )
        }
    }


    val changeLocation = View.OnClickListener {
        updateEvent(WeatherEvents.ChangeLocation)
    }

    val onBackClicked = View.OnClickListener {
        updateEvent(WeatherEvents.CallHomeScreen)
    }

}