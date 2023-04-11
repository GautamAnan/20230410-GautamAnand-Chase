package com.gautam.domain.model

data class SysModel(
    val type: Int? = 0,
   val id: Int? = 0,
   val country: String? = "",
   val sunrise: Long? = 0L,
   val sunset: Long? = 0L,
)
