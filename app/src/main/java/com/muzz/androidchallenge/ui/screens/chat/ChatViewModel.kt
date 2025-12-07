package com.muzz.androidchallenge.ui.screens.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzz.androidchallenge.domain.interactors.GetAllMessages
import com.muzz.androidchallenge.domain.interactors.GetCurrentUser
import com.muzz.androidchallenge.domain.interactors.SendMessage
import com.muzz.androidchallenge.domain.interactors.SetCurrentUser
import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.domain.models.Result
import com.muzz.androidchallenge.domain.models.User
import com.muzz.androidchallenge.ui.utils.DateTimeFormatter
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
    private val sendMessage: SendMessage,
    private val getCurrentUser: GetCurrentUser,
    private val setCurrentUser: SetCurrentUser
) : ViewModel() {
    private val _state = MutableStateFlow(ChatViewState())
    val uiState = _state.asStateFlow()

    init {
        observeCurrentUser()
        observeAllMessages()
    }

    fun observeCurrentUser() {
        getCurrentUser(Unit)
            .onEach { result ->
                if (result is Result.Success) {
                    _state.update { it.copy(currentUser = result.data) }
                }
            }.launchIn(viewModelScope)
    }

    fun observeAllMessages() {
        getAllMessages(Unit)
            .onEach { result ->
                when (result) {
                    is Result.Loading -> {
                        // Handle loading state here.
                    }

                    is Result.Success -> {
                        _state.update { it.copy(chatItems = processMessages(result.data)) }
                    }

                    is Result.Error -> {
                        // Handle error state here.
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun processMessages(messages: List<Message>): List<ChatListItem> {
        if (messages.isEmpty()) return emptyList()

        val result = mutableListOf<ChatListItem>()

        for (i in messages.indices) {
            val current = messages[i]
            val previous = messages.getOrNull(i - 1)

            val needsHeader =
                previous == null || (current.timestamp - previous.timestamp) > HEADER_BREAK_MILLISECONDS

            if (needsHeader) {
                result.add(ChatListItem.SectionHeader(DateTimeFormatter.format(current.timestamp)))
            }

            val grouped = previous != null && previous.senderId == current.senderId
                    && (current.timestamp - previous.timestamp) < GROUPING_WINDOW_MILLISECONDS

            result.add(ChatListItem.MessageItem(current, grouped))
        }

        return result
    }

    fun onSendMessageClick(message: String) {
        viewModelScope.launch {
            sendMessage.invoke(
                params = Message(
                    text = message,
                    senderId = _state.value.currentUser?.id ?: User.SARAH.id
                )
            ).collect()
        }
    }

    fun switchUser(user: User) {
        viewModelScope.launch {
            setCurrentUser(user.id).collect()
        }
    }

    companion object {
        private const val GROUPING_WINDOW_MILLISECONDS = 20_000L       // 20 second grouping rule.
        private const val HEADER_BREAK_MILLISECONDS = 3_600_000L       // 1 hour section rule.
    }
}