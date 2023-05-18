package com.gautam.weather.di


import com.gautam.weather.ui.WeatherViewModel
import com.gautam.weather.ui.location_picker.LocationPageViewModel
import com.gautam.weather.ui.view_weather.InformationPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::WeatherViewModel)
    viewModelOf(::InformationPageViewModel)
    viewModelOf(::LocationPageViewModel)

}
