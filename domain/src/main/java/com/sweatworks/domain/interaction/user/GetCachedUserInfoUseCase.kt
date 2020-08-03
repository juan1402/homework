package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.User

interface GetCachedUserInfoUseCase {
    suspend operator fun invoke(pageNumber: Int): Result<List<User>>
}