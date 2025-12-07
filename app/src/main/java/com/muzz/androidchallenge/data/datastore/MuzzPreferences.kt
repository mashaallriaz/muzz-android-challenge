package com.muzz.androidchallenge.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.muzz.androidchallenge.data.datastore.MuzzPreferencesKeys.CURRENT_USER_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MuzzPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {
    val activeUserId: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[CURRENT_USER_ID]
    }

    suspend fun setActiveUserId(userId: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_USER_ID] = userId
        }
    }
}