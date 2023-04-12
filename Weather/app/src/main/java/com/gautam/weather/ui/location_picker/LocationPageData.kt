package com.gautam.weather.ui.location_picker

import androidx.lifecycle.MutableLiveData
import com.gautam.core.BaseData


class LocationPageData(
    val textToSearch: MutableLiveData<String> = MutableLiveData("")
): BaseData()