package com.sweatworks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sweatworks.data.database.dao.FavoriteDao
import com.sweatworks.data.database.dao.UserInfoDao
import com.sweatworks.data.database.model.FavoriteEntity
import com.sweatworks.data.database.model.UserInfoEntity

@Database(
    entities = [UserInfoEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserInfoDataBase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun favoriteDao(): FavoriteDao
}