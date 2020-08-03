package com.sweatworks.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val gender: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val state: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val email: String,
    val phoneNumber: String,
    val cellPhoneNumber: String,
    val age: Int,
    val smallPicture: String,
    val mediumPicture: String,
    val largePicture: String,
    var favorite: Boolean = false
) : Parcelable