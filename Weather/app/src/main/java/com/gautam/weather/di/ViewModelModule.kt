package com.gautam.weather.di


import com.gautam.weather.ui.WeatherData
import com.gautam.weather.ui.WeatherViewModel
import com.gautam.weather.ui.location_picker.LocationPageData
import com.gautam.weather.ui.location_picker.LocationPageViewModel
import com.gautam.weather.ui.view_weather.InformationPageData
import com.gautam.weather.ui.view_weather.InformationPageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        WeatherViewModel(
            application = androidApplication(),
            data = WeatherData(),
            get(),
            get(),
            get()
        )
    }

    viewModel {
        InformationPageViewModel(
            application = androidApplication(),
            data = InformationPageData(),
        )
    }

    viewModel {
        LocationPageViewModel(
            application = androidApplication(),
            data = LocationPageData(),
        )
    }

}
