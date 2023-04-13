package com.gautam.weather.ui.location_picker

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gautam.core.BaseEvent

import com.gautam.core.SharedBaseFragment
import com.gautam.core.fundamentals.displayErrorDialog
import com.gautam.weather.R
import com.gautam.weather.databinding.FragmentLocationPickerBinding
import com.gautam.weather.databinding.FragmentWeatherInfoBinding
import com.gautam.weather.ui.WeatherData
import com.gautam.weather.ui.WeatherEvents
import com.gautam.weather.ui.WeatherViewModel
import com.gautam.weather.ui.location_picker.list_logs.LocationLogsListAdapter


class LocationPageFragment :
    SharedBaseFragment<FragmentLocationPickerBinding, LocationPageEvent, LocationPageData, LocationPageViewModel, WeatherEvents, WeatherData, WeatherViewModel>(
        WeatherViewModel::class,
        LocationPageViewModel::class,
        R.layout.fragment_location_picker
    ) {

    override fun eventUpdated(event: BaseEvent) {
        when (event) {
            is LocationPageEvent.OnLocationSelected -> {
                if (isConnected()) {
                    sharedViewModel.getWeatherByName(event.area)
                    sharedViewModel.updateEvent(WeatherEvents.CallHomeScreen)
                } else {
                    context?.displayErrorDialog("Network Not connected")
                }
            }
            is LocationPageEvent.OnCloseBtn -> {
                sharedViewModel.onBackClicked.invoke()
            }
        }
    }

    override fun setupView(view: View) {
        binding?.rvHistory?.layoutManager = LinearLayoutManager(activity)
        binding?.rvHistory?.adapter = LocationLogsListAdapter()
        viewModel.historyData.postValue(sharedViewModel.locations.toList())
    }

}

