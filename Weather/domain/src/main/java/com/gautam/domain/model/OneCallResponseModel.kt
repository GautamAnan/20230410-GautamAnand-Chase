package com.gautam.domain.model

data class OneCallResponseModel(
    val lat: Double? = 0.0,
    val long: Double? = 0.0,
    val timeZone: String? = "",
    val timeZoneOffSet: Int? = 0,
    val currentModel: CurrentModel? = null,
    val hourlyModel: List<HourlyModel>? = emptyList(),
    val dailyModel: List<DailyModel>? = emptyList(),
)
