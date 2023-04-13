package com.gautam.weather.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gautam.core.SharedBaseViewModel
import com.gautam.core.fundamentals.Error
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

    // Search the weather data as per the name of the location
    fun getWeatherByName(location: String) {
        viewModelScope.launch {
            data.loading()
            weatherUseCase.execute(CurrentWeatherParams(location)).mapResult(
                {
                    data.hideLoading()
                    data.locationSearchTexts.add(location)// add text to collection history as search was sucessful
                    data.model.postValue(it)
                }, {
                    data.error()
                    it as Error.RemoteError
                    updateEvent(WeatherEvents.CallError( "${it.code} - "+it.message))
                }
            )
        }
    }

    // Search the weather data as per the coordinates of the location
    fun getWeatherByLocation(latLng: LatLng) {
        viewModelScope.launch {
            data.loading()
            weatherByLocationUseCase.execute(CurrentWeatherByLocationParams(latLng)).mapResult(
                {
                    data.hideLoading()
                    data.model.postValue(it)
                }, {
                    data.error()
                    it as Error.RemoteError
                    updateEvent(WeatherEvents.CallError( "${it.code} - "+it.message))
                }
            )
        }
    }

    // location edit button was clicked
    val changeLocation = View.OnClickListener {
        updateEvent(WeatherEvents.ChangeLocation)
    }

    // on back button was clicked
    val onBackClicked = {
        updateEvent(WeatherEvents.CallHomeScreen)
    }

    val locations = data.locationSearchTexts

}