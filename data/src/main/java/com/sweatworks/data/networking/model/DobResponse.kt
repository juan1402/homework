package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName

data class DobResponse(
    @SerializedName("age")
    val age: Int
)