package com.gautam.domain.model

data class SysModel(
    var type: Int? = 0,
   var id: Int? = 0,
   var country: String? = "",
   var sunrise: Long? = 0L,
   var sunset: Long? = 0L,
)
