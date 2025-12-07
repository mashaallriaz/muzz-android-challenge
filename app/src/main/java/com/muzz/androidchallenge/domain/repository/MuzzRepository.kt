package com.muzz.androidchallenge.domain.repository

import com.muzz.androidchallenge.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface MuzzRepository {
    fun getAllMessages(): Flow<List<Message>>
    suspend fun sendMessage(message: Message)

    fun getCurrentUserId(): Flow<Int?>
    suspend fun setCurrentUserId(userId: Int)
}