package com.onesmartstar.heinhtetaung.q4b.di

import android.content.Context
import androidx.room.Room
import com.onesmartstar.heinhtetaung.q4b.data.local.Q4BDatabase
import com.onesmartstar.heinhtetaung.q4b.util.Constants.Q4B_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Q4BDatabase {
        return Room.databaseBuilder(
            context,
            Q4BDatabase::class.java,
            Q4B_DATABASE
        ).build()
    }

}