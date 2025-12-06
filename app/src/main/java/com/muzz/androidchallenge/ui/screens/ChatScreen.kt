package com.muzz.androidchallenge.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.muzz.androidchallenge.ui.screens.components.ChatInputBar
import com.muzz.androidchallenge.ui.screens.components.ChatTopAppBar
import com.muzz.androidchallenge.ui.screens.components.ReceivedChatBubble
import com.muzz.androidchallenge.ui.screens.components.SentChatBubble
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        val state by viewModel.uiState.collectAsState()
        val messagesList = state.messages.asReversed()
        val messagesListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        ChatTopAppBar()

        LazyColumn(
            modifier = Modifier.weight(1f),
            state = messagesListState,
            contentPadding = PaddingValues(vertical = MuzzSpacing.spacing12),
            reverseLayout = true
        ) {
            items(messagesList) { message ->
                SentChatBubble(message)
                ReceivedChatBubble(message)
            }
        }

        ChatInputBar(onSendMessage = { message ->
            viewModel.onSendMessageClick(message)
            coroutineScope.launch {
                if (messagesList.isNotEmpty()) {
                    messagesListState.scrollToItem(0)
                }
            }
        })
    }
}