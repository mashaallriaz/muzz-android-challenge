package com.muzz.androidchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MessageEntity::class], version = 1, exportSchema = false)
abstract class MuzzDatabase : RoomDatabase() {
    abstract fun messagesDao(): MessagesDao
}