package com.muzz.androidchallenge.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.muzz.androidchallenge.ui.screens.components.ChatTopAppBar

@Composable
fun ChatScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        ChatTopAppBar()
    }
}