package com.sweatworks.domain.repository

import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.User

interface UserInfoRepository {
    suspend fun getCachedUserData(pageNumber: Int): Result<List<User>>

    suspend fun getUserData(pageNumber: Int): Result<List<User>>

    suspend fun searchUsers(query: String): Result<List<User>>
}