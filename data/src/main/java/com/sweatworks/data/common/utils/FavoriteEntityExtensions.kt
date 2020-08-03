package com.sweatworks.data.common.utils

import com.sweatworks.data.database.model.FavoriteEntity
import com.sweatworks.data.networking.model.*

fun FavoriteEntity.toUserResponse() = UserResponse(
    gender,
    NameResponse(title, firstName, lastName),
    LocationResponse(country, state, city, CoordinateResponse(latitude, longitude)),
    email,
    DobResponse(age),
    phoneNumber,
    cellPhoneNumber,
    PictureResponse(smallPicture, mediumPicture, largePicture),
    favorite
)