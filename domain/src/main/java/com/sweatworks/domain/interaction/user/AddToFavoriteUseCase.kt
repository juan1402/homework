package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.User

interface AddToFavoriteUseCase {
    suspend operator fun invoke(user: User): Result<List<User>>
}