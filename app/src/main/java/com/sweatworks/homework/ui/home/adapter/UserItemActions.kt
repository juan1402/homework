package com.sweatworks.homework.ui.home.adapter

import com.sweatworks.domain.model.User

sealed class UserItemActions

data class FavoriteClick(val user: User) : UserItemActions()
data class ItemClick(val user: User) : UserItemActions()