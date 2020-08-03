package com.sweatworks.homework.ui.detail

import com.sweatworks.domain.model.User

sealed class DetailActions

data class AddToContacts(val user: User) : DetailActions()
data class OpenGoogleMap(val user: User) : DetailActions()