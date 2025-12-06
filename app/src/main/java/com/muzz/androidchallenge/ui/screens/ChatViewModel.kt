package com.muzz.androidchallenge.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzz.androidchallenge.domain.interactors.GetAllMessages
import com.muzz.androidchallenge.domain.interactors.SendMessage
import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.domain.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAllMessages: GetAllMessages,
    private val sendMessage: SendMessage
) : ViewModel() {
    private val _state = MutableStateFlow(ChatViewState())
    val uiState = _state.asStateFlow()

    init {
        getAllMessages()
    }

    fun getAllMessages() {
        getAllMessages(Unit)
            .onEach { result ->
                when (result) {
                    is Result.Loading -> {
                        // Handle loading state here.
                    }

                    is Result.Success -> {
                        _state.update { it.copy(messages = result.data) }
                    }

                    is Result.Error -> {
                        // Handle error state here.
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun onSendMessageClick(message: String) {
        viewModelScope.launch {
            sendMessage.invoke(Message(text = message, senderId = 1)).collect()
        }
    }
}