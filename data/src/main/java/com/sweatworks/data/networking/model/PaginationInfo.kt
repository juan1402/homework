package com.sweatworks.data.networking.model

import com.google.gson.annotations.SerializedName

data class PaginationInfo(
    @SerializedName("page")
    val pageNumber: Int
)