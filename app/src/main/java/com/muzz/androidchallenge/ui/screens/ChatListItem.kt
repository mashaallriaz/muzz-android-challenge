package com.muzz.androidchallenge.ui.screens

import com.muzz.androidchallenge.domain.models.Message

sealed class ChatListItem {
    data class SectionHeader(val timestampText: String) : ChatListItem()
    data class MessageItem(val message: Message, val isGrouped: Boolean) : ChatListItem()
}