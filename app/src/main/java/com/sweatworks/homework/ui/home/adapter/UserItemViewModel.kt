package com.sweatworks.homework.ui.home.adapter

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sweatworks.domain.model.User

class UserItemViewModel(
    private val userItemEvent: MutableLiveData<UserItemActions>,
    orientation: Int,
    user: User
) : ViewModel() {

    val currentUser = ObservableField<User>(user)
    val orientation = ObservableInt(orientation)

    fun onFavoriteClick() {
        currentUser.get()?.let {
            userItemEvent.value =
                FavoriteClick(it.apply { it.favorite = true })
        }
    }

    fun onUserItemClick() {
        currentUser.get()?.let {
            userItemEvent.value = ItemClick(it)
        }
    }
}