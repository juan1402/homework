package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName
import com.sweatworks.data.database.model.UserInfoEntity
import com.sweatworks.data.networking.base.RoomMapper

data class UserInfoResponse(
    @SerializedName("results")
    val results: List<UserResponse>,
    @SerializedName("info")
    val paginationInfo: PaginationInfo
) : RoomMapper<UserInfoEntity> {

    override fun mapToRoomEntity() =
        UserInfoEntity(paginationInfo.pageNumber, results)

}

