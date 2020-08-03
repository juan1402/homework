package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val nameResponse: NameResponse,
    @SerializedName("location")
    val locationResponse: LocationResponse,
    @SerializedName("email")
    val email: String,
    @SerializedName("dob")
    val dobResponse: DobResponse,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("cell")
    val cellPhoneNumber: String,
    @SerializedName("picture")
    val pictureResponse: PictureResponse,
    val favorite: Boolean = false
)