package com.onesmartstar.heinhtetaung.q4b.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onesmartstar.heinhtetaung.q4b.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllItemList(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItems(items: List<Item>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(item: Item)

    @Query("DELETE FROM item_table")
    suspend fun deleteAllItems()

}