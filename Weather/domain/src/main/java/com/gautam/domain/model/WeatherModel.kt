package com.gautam.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("main") val main: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("icon") val icon: String? = "",
)