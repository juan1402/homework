package com.sweatworks.homework.ui.splash

import com.sweatworks.domain.interaction.user.GetUserInfoUseCase
import com.sweatworks.domain.model.onFailure
import com.sweatworks.domain.model.onSuccess
import com.sweatworks.homework.common.utils.Event
import com.sweatworks.homework.common.utils.IntentEvent
import com.sweatworks.homework.ui.base.BaseViewModel
import com.sweatworks.homework.ui.home.HomeActivity
import java.util.concurrent.atomic.AtomicInteger

class SplashViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel() {

    private val userDatePageNumber = AtomicInteger(0)

    init {
        getUserInfo(userDatePageNumber.incrementAndGet())
    }

    private fun getUserInfo(pageNumber: Int) = executeUseCase {
        getUserInfoUseCase.invoke(pageNumber)
            .onSuccess { intentEvent.value = Event(IntentEvent(HomeActivity::class)) }
            .onFailure { snackBarError.value = it.throwable.message }
    }
}