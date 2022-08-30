package com.onesmartstar.heinhtetaung.q4b.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onesmartstar.heinhtetaung.q4b.util.Constants.ITEM_DATABASE_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = ITEM_DATABASE_TABLE)
data class Item(
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("fileOriginalName")
    val fileOriginalName: String,
    val type: String? = null,
    var downloadedUri: String? = null,
)