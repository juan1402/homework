package com.sweatworks.data.di

import com.sweatworks.data.common.utils.Connectivity
import com.sweatworks.data.common.utils.ConnectivityImpl
import com.sweatworks.data.repository.user.FavoriteRepositoryImpl
import com.sweatworks.data.repository.user.UserInfoRepositoryImpl
import com.sweatworks.domain.repository.FavoriteRepository
import com.sweatworks.domain.repository.UserInfoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserInfoRepository> { UserInfoRepositoryImpl(get(), get()) }
    factory<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
}