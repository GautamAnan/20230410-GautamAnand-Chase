package com.gautam.weather.ui.location_picker

import com.gautam.core.BaseEvent


sealed class LocationPageEvent: BaseEvent {
    data class OnLocationSelected(val area:String): LocationPageEvent()

    object OnCloseBtn:LocationPageEvent()
}