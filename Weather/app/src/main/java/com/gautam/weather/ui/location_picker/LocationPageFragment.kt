package com.gautam.weather.ui.location_picker

import android.view.View
import com.gautam.core.BaseEvent

import com.gautam.core.SharedBaseFragment
import com.gautam.weather.R
import com.gautam.weather.databinding.FragmentLocationPickerBinding
import com.gautam.weather.databinding.FragmentWeatherInfoBinding
import com.gautam.weather.ui.WeatherData
import com.gautam.weather.ui.WeatherEvents
import com.gautam.weather.ui.WeatherViewModel


class LocationPageFragment :
    SharedBaseFragment<FragmentLocationPickerBinding, LocationPageEvent, LocationPageData, LocationPageViewModel, WeatherEvents, WeatherData, WeatherViewModel>(
        WeatherViewModel::class,
        LocationPageViewModel::class,
        R.layout.fragment_location_picker
    ) {

    override fun eventUpdated(event: BaseEvent) {
        when (event) {
            is LocationPageEvent.OnLocationSelected -> {
                sharedViewModel.getWeatherByName(event.area)
            }
        }
    }

    override fun setupView(view: View) {

    }


}

