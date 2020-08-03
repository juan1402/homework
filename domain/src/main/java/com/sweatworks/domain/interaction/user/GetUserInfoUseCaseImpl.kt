package com.sweatworks.domain.interaction.user

import com.sweatworks.domain.repository.UserInfoRepository

class GetUserInfoUseCaseImpl(private val userRepository: UserInfoRepository) : GetUserInfoUseCase {
    override suspend fun invoke(pageNumber: Int) = userRepository.getUserData(pageNumber)
}