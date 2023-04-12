package com.gautam.weather.ui.location_picker

import android.app.Application
import com.gautam.core.BaseViewModel


class LocationPageViewModel(
    application: Application,
    data: LocationPageData
) : BaseViewModel<LocationPageData, LocationPageEvent>(application, data){

    val onActionDoneListener = {
        updateEvent(data.textToSearch.value?.let { LocationPageEvent.OnLocationSelected(it) })
    }


}