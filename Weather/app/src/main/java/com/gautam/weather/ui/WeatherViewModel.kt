package com.gautam.weather.ui

import android.app.Application
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gautam.core.fundamentals.Error
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.domain.usecase.CurrentWeatherByCityUseCase
import com.gautam.domain.usecase.CurrentWeatherByLocationParams
import com.gautam.domain.usecase.CurrentWeatherByLocationUseCase
import com.gautam.domain.usecase.CurrentWeatherParams
import com.gautam.weather.ui.view_weather.AccompanistPermissionsScreen
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: CurrentWeatherByCityUseCase,
    private val weatherByLocationUseCase: CurrentWeatherByLocationUseCase
) : ViewModel() {

    val result: MutableLiveData<WeatherState> = MutableLiveData(WeatherState.NoState)

    // Search the weather data as per the name of the location
    fun getWeatherByName(location: String) {
        viewModelScope.launch {
            result.value = WeatherState.Loading
            weatherUseCase.execute(CurrentWeatherParams(location)).mapResult(
                {
                    //  result.value = WeatherState.Response(it)
                    //data.locationSearchTexts.add(location)// add text to collection history as search was sucessful
                }, {
                    it as Error.RemoteError
                    // result.value = WeatherState.Error("${it.code} - ${it.message}")
                }
            )
        }
    }

    // Search the weather data as per the coordinates of the location
    fun getWeatherByLocation(latLng: LatLng) {


            viewModelScope.launch {
            result.value = WeatherState.Loading
            weatherByLocationUseCase.execute(CurrentWeatherByLocationParams(latLng)).mapResult(
                {
                    result.postValue(WeatherState.Response(it))

                }, {
                    it as Error.RemoteError
                    result.postValue(WeatherState.Error("${it.code} - ${it.message}"))
                }
            )
        }
    }

    // location edit button was clicked
    val changeLocation = View.OnClickListener {
        //updateEvent(WeatherEvents.ChangeLocation)
    }

    // on back button was clicked
    val onBackClicked = {
        // updateEvent(WeatherEvents.CallHomeScreen)
    }

    //val locations = data.locationSearchTexts

}