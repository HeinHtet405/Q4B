package com.onesmartstar.heinhtetaung.q4b.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.onesmartstar.heinhtetaung.q4b.domain.repository.DataStoreOperations
import com.onesmartstar.heinhtetaung.q4b.util.Constants.PREFERENCES_NAME
import com.onesmartstar.heinhtetaung.q4b.util.Constants.PREF_KEY_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        val token = stringPreferencesKey(name = PREF_KEY_TOKEN)
    }

    private val dataStore = context.dataStore

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.token] = token
        }
    }

    override fun readToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val token = preferences[PreferencesKey.token] ?: ""
                token
            }
    }
}