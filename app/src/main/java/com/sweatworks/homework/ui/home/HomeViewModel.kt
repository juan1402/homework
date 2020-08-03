package com.sweatworks.homework.ui.home

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.sweatworks.domain.interaction.user.AddToFavoriteUseCase
import com.sweatworks.domain.interaction.user.GetCachedUserInfoUseCase
import com.sweatworks.domain.interaction.user.GetFavoriteUseCase
import com.sweatworks.domain.interaction.user.GetUserInfoUseCase
import com.sweatworks.domain.interaction.user.SearchUserUseCase
import com.sweatworks.domain.model.User
import com.sweatworks.domain.model.onFailure
import com.sweatworks.domain.model.onSuccess
import com.sweatworks.homework.common.utils.Event
import com.sweatworks.homework.common.utils.IntentEvent
import com.sweatworks.homework.ui.base.BaseActivity
import com.sweatworks.homework.ui.base.BaseViewModel
import com.sweatworks.homework.ui.detail.DetailActivity
import java.util.concurrent.atomic.AtomicInteger

class HomeViewModel(
    private val getCachedUserInfoUseCase: GetCachedUserInfoUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val searchUserUseCase: SearchUserUseCase,
    private val saveFavoriteUseCase: AddToFavoriteUseCase
) : BaseViewModel(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private val cachedUserDatePageNumber = AtomicInteger(0)
    private val userDatePageNumber = AtomicInteger(0)

    init {
        loadMoreCachedUsers()
        loadMoreUsers()
    }

    val reloadedHeaderData = MutableLiveData<List<User>>()
    val searchUserData = MutableLiveData<List<User>>()
    val headerData = MutableLiveData<List<User>>()
    val contentData = MutableLiveData<List<User>>()

    private fun getUserInfo(pageNumber: Int) = executeUseCase {
        getUserInfoUseCase.invoke(pageNumber)
            .onSuccess { contentData.value = it }
            .onFailure { onError(it) }
    }

    private fun getHeaderUsers(
        pageNumber: Int,
        shouldReload: Boolean = false
    ) = ArrayList<User>().apply {
        executeUseCase {
            getFavoriteUseCase()
                .onSuccess { this.addAll(it) }
                .onFailure { onError(it) }
        }.invokeOnCompletion {
            executeUseCase {
                getCachedUserInfoUseCase.invoke(pageNumber)
                    .onSuccess { it ->
                        this.addAll(it)
                        if (shouldReload)
                            reloadedHeaderData.value =
                                this.distinctBy { it.firstName }
                        else headerData.value = this
                    }
                    .onFailure { onError(it) }
            }
        }
    }

    private fun getSearchResults(query: String) = executeUseCase {
        searchUserUseCase.invoke(query)
            .onSuccess { searchUserData.value = it }
            .onFailure { onError(it) }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        getSearchResults(query.orEmpty())
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        getSearchResults(newText.orEmpty())
        return false
    }

    override fun onClose(): Boolean {
        headerData.value = contentData.value
        return false
    }

    fun addToFavoriteAndReload(user: User) = executeUseCase {
        saveFavoriteUseCase.invoke(user)
            .onSuccess { getHeaderUsers(userDatePageNumber.get(), true) }
            .onFailure { onError(it) }
    }

    fun fireDetailEvent(user: User) = intentEvent.apply {
        value = Event(IntentEvent(DetailActivity::class, detailArguments(user), false))
    }

    private fun detailArguments(user: User) = Bundle().apply {
        putParcelable(BaseActivity.BASE_ARGUMENTS, user)
    }

    fun loadMoreCachedUsers() =
        getHeaderUsers(cachedUserDatePageNumber.incrementAndGet())

    fun loadMoreUsers() =
        getUserInfo(userDatePageNumber.incrementAndGet())
}