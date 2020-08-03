package com.sweatworks.domain.di

import com.sweatworks.domain.interaction.user.GetCachedUserInfoUseCase
import com.sweatworks.domain.interaction.user.GetCachedUserInfoUseCaseImpl
import com.sweatworks.domain.interaction.user.GetFavoriteUseCase
import com.sweatworks.domain.interaction.user.GetFavoriteUseCaseImpl
import com.sweatworks.domain.interaction.user.GetUserInfoUseCase
import com.sweatworks.domain.interaction.user.GetUserInfoUseCaseImpl
import com.sweatworks.domain.interaction.user.AddToFavoriteUseCase
import com.sweatworks.domain.interaction.user.AddToFavoriteUseCaseImpl
import com.sweatworks.domain.interaction.user.SearchUseCaseImpl
import com.sweatworks.domain.interaction.user.SearchUserUseCase
import org.koin.dsl.module

val interactionModule = module {
    factory<GetCachedUserInfoUseCase> { GetCachedUserInfoUseCaseImpl(get()) }
    factory<GetFavoriteUseCase> { GetFavoriteUseCaseImpl(get()) }
    factory<GetUserInfoUseCase> { GetUserInfoUseCaseImpl(get()) }
    factory<AddToFavoriteUseCase> { AddToFavoriteUseCaseImpl(get()) }
    factory<SearchUserUseCase> { SearchUseCaseImpl(get()) }
}
