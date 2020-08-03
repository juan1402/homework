package com.sweatworks.data.di

import androidx.room.Room
import com.sweatworks.data.database.UserInfoDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val USER_DB = "user-database"

val databaseModule = module {
  single {
    //TODO remove fallbackToDestructiveMigration when this goes to production
    Room.databaseBuilder(androidContext(), UserInfoDataBase::class.java, USER_DB)
      .fallbackToDestructiveMigration().build()
  }
  factory { get<UserInfoDataBase>().userInfoDao() }
  factory { get<UserInfoDataBase>().favoriteDao() }
}