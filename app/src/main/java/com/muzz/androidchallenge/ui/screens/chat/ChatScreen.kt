package com.muzz.androidchallenge.ui.screens.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.muzz.androidchallenge.ui.screens.components.DateTimeHeader
import com.muzz.androidchallenge.ui.screens.components.ReceivedChatBubble
import com.muzz.androidchallenge.ui.screens.components.SentChatBubble
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        val state by viewModel.uiState.collectAsState()
        val chatItems = state.chatItems.asReversed()
        val chatItemsListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        ChatTopAppBar(currentUser = state.currentUser, onSwitchUser = viewModel::switchUser)

        LazyColumn(
            modifier = Modifier.weight(1f),
            state = chatItemsListState,
            contentPadding = PaddingValues(vertical = MuzzSpacing.spacing12),
            reverseLayout = true
        ) {
            items(chatItems) { item ->
                when (item) {
                    is ChatListItem.SectionHeader -> {
                        DateTimeHeader(timestampText = item.timestampText)
                        Spacer(Modifier.height(MuzzSpacing.spacing8))
                    }

                    is ChatListItem.MessageItem -> {
                        if (item.message.senderId == state.currentUser?.id) {
                            SentChatBubble(message = item.message, isGrouped = item.isGrouped)
                        } else {
                            ReceivedChatBubble(message = item.message, isGrouped = item.isGrouped)
                        }
                    }
                }
            }
        }

        ChatInputBar(onSendMessage = { message ->
            viewModel.onSendMessageClick(message)
            coroutineScope.launch {
                if (chatItems.isNotEmpty()) {
                    chatItemsListState.scrollToItem(0)
                }
            }
        })
    }
}