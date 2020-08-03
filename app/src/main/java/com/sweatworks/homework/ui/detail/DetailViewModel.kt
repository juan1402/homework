package com.sweatworks.homework.ui.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.sweatworks.domain.interaction.user.AddToFavoriteUseCase
import com.sweatworks.domain.interaction.user.GetUserInfoUseCase
import com.sweatworks.domain.model.User
import com.sweatworks.domain.model.onFailure
import com.sweatworks.domain.model.onSuccess
import com.sweatworks.homework.common.utils.IntentEvent
import com.sweatworks.homework.ui.base.BaseActivity
import com.sweatworks.homework.ui.base.BaseViewModel
import java.util.concurrent.atomic.AtomicInteger

class DetailViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase
) : BaseViewModel() {

    val currentUser = ObservableField<User>()
    val userData = MutableLiveData<List<User>>()
    val detailActions = MutableLiveData<DetailActions>()
    private val userDatePageNumber = AtomicInteger(0)

    init {
        getUserInfo(userDatePageNumber.incrementAndGet())
    }

    private fun getUserInfo(pageNumber: Int) = executeUseCase {
        getUserInfoUseCase.invoke(pageNumber)
            .onSuccess { userData.value = it }
            .onFailure { onError(it) }
    }

    fun addToFavorites(user: User) = executeUseCase {
        addToFavoriteUseCase.invoke(user).onFailure { onError(it) }
    }

    fun fireDetailEvent(user: User) = intentEvent.apply {
        value = IntentEvent(DetailActivity::class, detailArguments(user))
    }

    private fun detailArguments(user: User) = Bundle().apply {
        putParcelable(BaseActivity.BASE_ARGUMENTS, user)
    }

    fun loadMoreUsers() =
        getUserInfo(userDatePageNumber.incrementAndGet())

    fun addToContacts() {
        currentUser.get()?.let {
            detailActions.value = AddToContacts(it)
        }
    }

    fun openInGoogleMaps() {
        currentUser.get()?.let {
            detailActions.value = AddToContacts(it)
        }
    }
}