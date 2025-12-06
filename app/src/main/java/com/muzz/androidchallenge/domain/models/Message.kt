package com.muzz.androidchallenge.domain.models

data class Message(
    val id: Long = 0,
    val senderId: Int,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)