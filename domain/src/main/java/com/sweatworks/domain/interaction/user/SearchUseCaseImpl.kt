package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.repository.UserInfoRepository

class SearchUseCaseImpl(private val repository: UserInfoRepository): SearchUserUseCase {
    override suspend fun invoke(query: String) = repository.searchUsers(query)
}