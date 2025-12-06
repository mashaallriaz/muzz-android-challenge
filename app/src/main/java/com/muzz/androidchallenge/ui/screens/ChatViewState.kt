package com.muzz.androidchallenge.ui.screens

import com.muzz.androidchallenge.domain.models.Message

data class ChatViewState (
    val messages: List<Message> = listOf()
)