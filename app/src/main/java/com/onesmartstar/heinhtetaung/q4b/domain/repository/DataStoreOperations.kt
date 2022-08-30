package com.onesmartstar.heinhtetaung.q4b.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveToken(token : String)
    fun readToken(): Flow<String>
}