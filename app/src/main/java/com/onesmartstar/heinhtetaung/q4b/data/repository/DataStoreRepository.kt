package com.onesmartstar.heinhtetaung.q4b.data.repository

import com.onesmartstar.heinhtetaung.q4b.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStoreOperations
) {
    suspend fun saveToken(token: String) {
        dataStore.saveToken(token = token)
    }

    fun readToken(): Flow<String> {
        return dataStore.readToken()
    }
}