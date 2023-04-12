package com.gautam.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    var id: Int? = 0,
    var main: String? = "",
    var description: String? = "",
    var icon: String? = "",
)