package com.gautam.weather.ui.view_weather

import android.app.Application
import com.gautam.core.BaseViewModel


class InformationPageViewModel(
    application: Application,
    data: InformationPageData
) : BaseViewModel<InformationPageData, InformationPageEvent>(application, data)