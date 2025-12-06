package com.muzz.androidchallenge.ui.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(ChatViewState())
    val uiState = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                messages = listOf(
                    "Hello, how are you?",
                    "It is nice to meet you.",
                    "When can we hang out?"
                )
            )
        }
    }

    fun onSendMessageClick(message: String) {
        _state.update { it.copy(messages = it.messages + message) }
    }
}