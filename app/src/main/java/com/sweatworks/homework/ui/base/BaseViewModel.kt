package com.sweatworks.homework.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sweatworks.domain.model.HttpError
import com.sweatworks.homework.common.extensions.launch
import com.sweatworks.homework.common.utils.Event
import com.sweatworks.homework.common.utils.IntentEvent
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    val intentEvent = MutableLiveData<Event<IntentEvent>>()
    val snackBarError = MutableLiveData<String>()

    protected fun executeUseCase(action: suspend () -> Unit) =
        launch { action() }

    fun onError(error: HttpError) {
        snackBarError.value = error.throwable.message
    }
}