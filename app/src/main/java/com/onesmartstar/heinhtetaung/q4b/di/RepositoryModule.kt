package com.onesmartstar.heinhtetaung.q4b.di

import android.content.Context
import com.onesmartstar.heinhtetaung.q4b.data.repository.DataStoreOperationsImpl
import com.onesmartstar.heinhtetaung.q4b.domain.repository.DataStoreOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

}