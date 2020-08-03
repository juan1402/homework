package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.repository.UserInfoRepository

class GetCachedUserInfoUseCaseImpl(private val userRepository: UserInfoRepository) : GetCachedUserInfoUseCase {
    override suspend fun invoke(pageNumber: Int) = userRepository.getCachedUserData(pageNumber)
}