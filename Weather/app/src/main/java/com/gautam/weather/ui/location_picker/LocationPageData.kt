package com.gautam.weather.ui.location_picker

import androidx.lifecycle.MutableLiveData
import com.gautam.core.BaseData


class LocationPageData(
    val textToSearch: MutableLiveData<String> = MutableLiveData(""),
    val listHistory: MutableLiveData<List<String>> = MutableLiveData(emptyList()),
) : BaseData()