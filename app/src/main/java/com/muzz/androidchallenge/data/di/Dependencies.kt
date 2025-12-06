package com.muzz.androidchallenge.data.di

import android.content.Context
import androidx.room.Room
import com.muzz.androidchallenge.data.database.MessagesDao
import com.muzz.androidchallenge.data.database.MuzzDatabase
import com.muzz.androidchallenge.data.repository.MuzzRepositoryImpl
import com.muzz.androidchallenge.domain.repository.MuzzRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Dependencies {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MuzzDatabase =
        Room.databaseBuilder(appContext, MuzzDatabase::class.java, "muzz_db").build()

    @Provides
    fun provideMessagesDao(database: MuzzDatabase): MessagesDao = database.messagesDao()

    @Provides
    fun provideMuzzRepository(messagesDao: MessagesDao): MuzzRepository {
        return MuzzRepositoryImpl(messagesDao)
    }
}