package com.gautam.weather.di


import com.gautam.weather.ui.location_picker.SearchLocationViewModel
import com.gautam.weather.ui.view_weather.InformationPageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        InformationPageViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel {
        SearchLocationViewModel(
            get()
        )
    }

}
