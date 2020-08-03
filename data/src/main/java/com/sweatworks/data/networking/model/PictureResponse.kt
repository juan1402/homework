package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    @SerializedName("thumbnail")
    val smallPicture: String,
    @SerializedName("medium")
    val mediumPicture: String,
    @SerializedName("large")
    val largePicture: String
)