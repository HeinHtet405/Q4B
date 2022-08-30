package com.onesmartstar.heinhtetaung.q4b.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.onesmartstar.heinhtetaung.q4b.data.local.dao.ItemDao
import com.onesmartstar.heinhtetaung.q4b.domain.model.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class Q4BDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): Q4BDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, Q4BDatabase::class.java)
            } else {
                Room.databaseBuilder(context, Q4BDatabase::class.java, "q4b_database.db")
            }
            return databaseBuilder.fallbackToDestructiveMigration().build()
        }
    }

    abstract fun itemDao(): ItemDao

}