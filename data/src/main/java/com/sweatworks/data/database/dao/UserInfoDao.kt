package com.sweatworks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sweatworks.data.database.USER_INFO_TABLE_NAME
import com.sweatworks.data.database.model.UserInfoEntity
import com.sweatworks.data.networking.model.UserResponse

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserInformation(userInfoEntity: UserInfoEntity)

    @Query("SELECT * FROM $USER_INFO_TABLE_NAME WHERE pageNumber = :page LIMIT 1")
    suspend fun getUserInformation(page: Int): UserInfoEntity

    @Query("SELECT * FROM $USER_INFO_TABLE_NAME")
    suspend fun getResults(): List<UserInfoEntity>
}