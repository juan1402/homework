package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName

data class NameResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String
)