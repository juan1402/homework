package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.User

interface SearchUserUseCase {
    suspend operator fun invoke(query: String): Result<List<User>>
}