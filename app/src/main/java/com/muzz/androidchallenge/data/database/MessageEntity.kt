package com.muzz.androidchallenge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muzz.androidchallenge.domain.models.Message

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val senderId: Int,
    val text: String,
    val timestamp: Long
)

fun List<MessageEntity>.toMessagesList(): List<Message> = map { entity ->
    Message(
        id = entity.id,
        senderId = entity.senderId,
        text = entity.text,
        timestamp = entity.timestamp
    )
}

fun Message.toMessageEntity(): MessageEntity {
    return MessageEntity(
        id = id,
        senderId = senderId,
        text = text,
        timestamp = timestamp
    )
}