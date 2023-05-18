
package com.gautam.weather.ui.location_picker

import android.view.View
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import com.gautam.weather.R

import com.gautam.weather.ui.WeatherViewModel

@Composable
fun LocationPage(navController: NavController, viewModel: WeatherViewModel)
  /*  SharedBaseFragment<FragmentLocationPickerBinding, LocationPageEvent, LocationPageData, LocationPageViewModel, WeatherEvents, WeatherData, WeatherViewModel>(
        WeatherViewModel::class,
        LocationPageViewModel::class,
        R.layout.fragment_location_picker
    )*/ {

   /* override fun eventUpdated(event: BaseEvent) {
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
*/
}


