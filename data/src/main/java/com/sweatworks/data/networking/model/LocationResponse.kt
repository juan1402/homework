package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinateResponse: CoordinateResponse
)