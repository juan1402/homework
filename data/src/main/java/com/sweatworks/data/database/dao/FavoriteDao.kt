package com.sweatworks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sweatworks.data.database.USER_FAVORITE_TABLE_NAME
import com.sweatworks.data.database.model.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteUser(userInfoEntity: FavoriteEntity): Long?

    @Query("SELECT * FROM $USER_FAVORITE_TABLE_NAME")
    suspend fun getFavoriteUsers(): List<FavoriteEntity>
}