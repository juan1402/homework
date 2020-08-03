package com.sweatworks.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sweatworks.data.database.USER_INFO_TABLE_NAME
import com.sweatworks.data.networking.base.DomainMapper
import com.sweatworks.data.networking.model.UserResponse
import com.sweatworks.domain.model.User

@Entity(tableName = USER_INFO_TABLE_NAME)
data class UserInfoEntity(
    @PrimaryKey val pageNumber: Int,
    val results: List<UserResponse>
) : DomainMapper<List<User>> {

    override fun mapToDomainModel() = results.map {
        User(
            it.gender,
            it.nameResponse.title,
            it.nameResponse.firstName,
            it.nameResponse.lastName,
            it.locationResponse.city,
            it.locationResponse.state,
            it.locationResponse.country,
            it.locationResponse.coordinateResponse.latitude,
            it.locationResponse.coordinateResponse.longitude,
            it.email,
            it.phoneNumber,
            it.cellPhoneNumber,
            it.dobResponse.age,
            it.pictureResponse.smallPicture,
            it.pictureResponse.mediumPicture,
            it.pictureResponse.largePicture,
            it.favorite
        )
    }

}
