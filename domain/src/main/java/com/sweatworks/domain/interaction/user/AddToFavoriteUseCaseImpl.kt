package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.model.User
import com.sweatworks.domain.repository.FavoriteRepository

class AddToFavoriteUseCaseImpl(
    private val userRepository: FavoriteRepository
) : AddToFavoriteUseCase {
    override suspend fun invoke(user: User) = userRepository.addToFavorite(user)
}