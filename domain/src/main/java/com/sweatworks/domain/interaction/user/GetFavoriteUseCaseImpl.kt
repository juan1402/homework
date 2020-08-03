package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.repository.FavoriteRepository

class GetFavoriteUseCaseImpl(private val userRepository: FavoriteRepository) : GetFavoriteUseCase {
    override suspend fun invoke() = userRepository.getFavorites()
}