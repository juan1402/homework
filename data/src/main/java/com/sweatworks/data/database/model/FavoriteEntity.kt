package com.sweatworks.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sweatworks.data.database.USER_FAVORITE_TABLE_NAME
import com.sweatworks.data.networking.base.DomainMapper
import com.sweatworks.domain.model.User

@Entity(tableName = USER_FAVORITE_TABLE_NAME)
data class FavoriteEntity(
    val gender: String,
    val title: String,
    @PrimaryKey
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
    val favorite: Boolean
) : DomainMapper<User> {

    override fun mapToDomainModel() = User(
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
        favorite
    )
}