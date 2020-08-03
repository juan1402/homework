package com.sweatworks.data.repository.user

import com.sweatworks.data.common.utils.toFavoriteEntity
import com.sweatworks.data.common.utils.toUserResponse
import com.sweatworks.data.database.dao.FavoriteDao
import com.sweatworks.data.database.model.UserInfoEntity
import com.sweatworks.data.repository.base.BaseRepository
import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.User
import com.sweatworks.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : BaseRepository<List<User>, UserInfoEntity>(), FavoriteRepository {

    override suspend fun getFavorites(): Result<List<User>> =
        fetchDbData {
            UserInfoEntity(
                Int.MAX_VALUE, favoriteDao
                    .getFavoriteUsers().map { it.toUserResponse() })
        }

    override suspend fun addToFavorite(user: User) =
        fetchDbData {
            val entity = user.toFavoriteEntity()
            val insertionKey = favoriteDao
                .saveFavoriteUser(entity)

            if (insertionKey != null) {
                return@fetchDbData UserInfoEntity(
                    Int.MAX_VALUE,
                    listOf(entity.toUserResponse())
                )
            } else {
                return@fetchDbData null
            }
        }
}