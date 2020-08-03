package com.sweatworks.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sweatworks.data.networking.model.*

class Converters {
    private val gson = Gson()

    // User Response

    @TypeConverter
    fun fromUserResponseListToJson(response: List<UserResponse>?): String {
        return response?.let { gson.toJson(response) } ?: ""
    }

    @TypeConverter
    fun fromJsonToUserResponseListResponse(jsonResponse: String?): List<UserResponse> {
        val listType = object : TypeToken<List<UserResponse>>() {}.type
        return gson.fromJson(jsonResponse, listType)
    }

    // Name Response

    @TypeConverter
    fun fromNameResponseToJson(response: NameResponse?): String {
        return response?.let { gson.toJson(response) } ?: ""
    }

    @TypeConverter
    fun fromJsonToNameResponse(jsonResponse: String?): NameResponse {
        val listType = object : TypeToken<NameResponse>() {}.type
        return gson.fromJson(jsonResponse, listType)
    }

    // Location Response

    @TypeConverter
    fun fromLocationResponseToJson(response: LocationResponse?): String {
        return response?.let { gson.toJson(response) } ?: ""
    }

    @TypeConverter
    fun fromJsonToLocationResponse(jsonResponse: String?): LocationResponse {
        val listType = object : TypeToken<LocationResponse>() {}.type
        return gson.fromJson(jsonResponse, listType)
    }

    // Coordinate Response

    @TypeConverter
    fun fromCoordinateResponseToJson(response: CoordinateResponse?): String {
        return response?.let { gson.toJson(response) } ?: ""
    }

    @TypeConverter
    fun fromJsonToCoordinateResponse(jsonResponse: String?): CoordinateResponse {
        val listType = object : TypeToken<CoordinateResponse>() {}.type
        return gson.fromJson(jsonResponse, listType)
    }

    // Dob Response

    @TypeConverter
    fun fromDobResponseToJson(response: DobResponse?): String {
        return response?.let { gson.toJson(response) } ?: ""
    }

    @TypeConverter
    fun fromJsonToDobResponse(jsonResponse: String?): DobResponse {
        val listType = object : TypeToken<DobResponse>() {}.type
        return gson.fromJson(jsonResponse, listType)
    }

    // Picture Response

    @TypeConverter
    fun fromPictureResponseToJson(response: PictureResponse?): String {
        return response?.let { gson.toJson(response) } ?: ""
    }

    @TypeConverter
    fun fromJsonToPictureResponse(jsonResponse: String?): PictureResponse {
        val listType = object : TypeToken<PictureResponse>() {}.type
        return gson.fromJson(jsonResponse, listType)
    }
}