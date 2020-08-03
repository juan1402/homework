package com.sweatworks.data.common.utils

import com.sweatworks.data.database.model.FavoriteEntity
import com.sweatworks.domain.model.User

fun User.toFavoriteEntity() = FavoriteEntity(
    gender,
    title,
    firstName,
    lastName,
    city,
    state,
    country,
    latitude,
    longitude,
    email,
    phoneNumber,
    cellPhoneNumber,
    age,
    smallPicture,
    mediumPicture,
    largePicture,
    true
)