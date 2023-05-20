package com.gautam.weather.ui.view_weather

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gautam.core.fundamentals.Error
import com.gautam.core.utils.ImagesCache
import com.gautam.domain.usecase.CurrentWeatherByCityUseCase
import com.gautam.domain.usecase.CurrentWeatherByLocationParams
import com.gautam.domain.usecase.CurrentWeatherByLocationUseCase
import com.gautam.domain.usecase.CurrentWeatherParams
import com.gautam.domain.usecase.HistoryParams
import com.gautam.domain.usecase.SetHistoryUseCase
import com.gautam.weather.ui.WeatherState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch


class InformationPageViewModel(
    private val setHistoryUseCase: SetHistoryUseCase,
    private val weatherUseCase: CurrentWeatherByCityUseCase,
    private val weatherByLocationUseCase: CurrentWeatherByLocationUseCase
) : ViewModel() {
    val imageOffline: MutableLiveData<Bitmap?> = MutableLiveData()
    val result: MutableLiveData<WeatherState> = MutableLiveData(WeatherState.NoState)

    fun checkImageIfAvailable(iconName: String?): Boolean {
        val image = ImagesCache.getImageFromWarehouse(iconName)
        image?.let {
            imageOffline.postValue(it)
        }
        return image != null
    }

    // Search the weather data as per the name of the location
    fun getWeatherByName(location: String) {
        viewModelScope.launch {
            result.value = WeatherState.Loading
            weatherUseCase.execute(CurrentWeatherParams(location)).mapResult(
                {
                    result.postValue(WeatherState.Response(it))
                    saveLocationName(it.name)
                }, {
                    when (it) {
                        is Error.RemoteError -> result.postValue(WeatherState.Error("${it.code} - ${it.message}"))
                        is Error.GenericError -> result.postValue(WeatherState.Error("${it.message} "))
                        else -> {}
                    }
                }
            )
        }
    }

    private fun saveLocationName(location: String?) {
        if (location != null) {
            viewModelScope.launch {
                setHistoryUseCase.execute(HistoryParams(location)).mapResult(
                    {
                        Log.d("History", "saveLocationName Success")
                    }, {
                        when (it) {
                            is Error.RemoteError -> {}
                            is Error.GenericError -> {}
                            else -> {}
                        }
                    }
                )
            }
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
                    when (it) {
                        is Error.RemoteError -> result.postValue(WeatherState.Error("${it.code} - ${it.message}"))
                        is Error.GenericError -> result.postValue(WeatherState.Error("${it.message} "))
                        else -> {}
                    }
                }
            )
        }
    }

}