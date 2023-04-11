package com.gautam.data.entity
import com.google.gson.annotations.SerializedName

data class Cloud(
    @SerializedName("all") val all: Int? = 0,
) : Entity()
