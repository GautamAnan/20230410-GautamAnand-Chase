package com.gautam.weather.ui.view_weather

import com.gautam.core.BaseEvent


sealed class InformationPageEvent: BaseEvent {
    object OnLinkSelected: InformationPageEvent()
}