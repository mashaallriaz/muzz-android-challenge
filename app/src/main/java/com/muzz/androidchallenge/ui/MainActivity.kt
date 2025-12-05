package com.muzz.androidchallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.muzz.androidchallenge.ui.screens.ChatScreen
import com.muzz.androidchallenge.ui.theme.MuzzAndroidTheme
import com.muzz.androidchallenge.ui.theme.MuzzColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MuzzAndroidTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MuzzColor.White),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MuzzColor.White)
                            .padding(innerPadding)
                    ) {
                        ChatScreen()
                    }
                }
            }
        }
    }
}