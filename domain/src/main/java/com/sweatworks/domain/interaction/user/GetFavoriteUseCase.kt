package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.User

interface GetFavoriteUseCase {
    suspend operator fun invoke(): Result<List<User>>
}