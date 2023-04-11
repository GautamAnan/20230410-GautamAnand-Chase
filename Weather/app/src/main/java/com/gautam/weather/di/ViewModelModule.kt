package com.gautam.weather.di



import com.gautam.weather.ui.WeatherData
import com.gautam.weather.ui.WeatherViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        WeatherViewModel(
            application = androidApplication(),
            data = WeatherData(),
        )
    }

}
