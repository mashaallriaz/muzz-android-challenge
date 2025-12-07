package com.muzz.androidchallenge.data.repository

import com.muzz.androidchallenge.data.database.MessagesDao
import com.muzz.androidchallenge.data.database.toMessageEntity
import com.muzz.androidchallenge.data.database.toMessagesList
import com.muzz.androidchallenge.data.datastore.MuzzPreferences
import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.domain.repository.MuzzRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MuzzRepositoryImpl @Inject constructor(
    private val messagesDao: MessagesDao,
    private val muzzPreferences: MuzzPreferences
) : MuzzRepository {
    override fun getAllMessages(): Flow<List<Message>> {
        return messagesDao.getAllMessagesFromDatabase().map { it.toMessagesList() }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun sendMessage(message: Message) {
        withContext(Dispatchers.IO) {
            messagesDao.insertMessage(message.toMessageEntity())
        }
    }

    override fun getCurrentUserId(): Flow<Int?> {
        return muzzPreferences.activeUserId
    }

    override suspend fun setCurrentUserId(userId: Int) {
        muzzPreferences.setActiveUserId(userId)
    }
}