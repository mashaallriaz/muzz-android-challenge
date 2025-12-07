package com.muzz.androidchallenge.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.muzz.androidchallenge.data.database.MessagesDao
import com.muzz.androidchallenge.data.database.MuzzDatabase
import com.muzz.androidchallenge.data.datastore.MuzzPreferences
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
    fun provideMuzzRepository(
        messagesDao: MessagesDao,
        muzzPreferences: MuzzPreferences
    ): MuzzRepository {
        return MuzzRepositoryImpl(messagesDao, muzzPreferences)
    }


    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.dataStoreFile("user_prefs.preferences_pb") }
        )
    }
}