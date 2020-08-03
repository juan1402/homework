package com.sweatworks.domain.repository

import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.User

interface FavoriteRepository {
    suspend fun getFavorites(): Result<List<User>>

    suspend fun addToFavorite(user: User): Result<List<User>>
}