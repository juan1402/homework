package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName

data class CoordinateResponse(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)