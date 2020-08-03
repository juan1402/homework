package com.sweatworks.data.networking

import com.sweatworks.data.networking.model.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api/")
    suspend fun getUserInfo(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): Response<UserInfoResponse>
}