package com.muzz.androidchallenge.ui.screens.chat

import com.muzz.androidchallenge.domain.models.User

data class ChatViewState (
    val currentUser: User? = null,
    val chatItems: List<ChatListItem> = listOf()
)