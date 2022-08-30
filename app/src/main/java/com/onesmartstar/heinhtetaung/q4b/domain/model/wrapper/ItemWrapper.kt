package com.onesmartstar.heinhtetaung.q4b.domain.model.wrapper

import com.onesmartstar.heinhtetaung.q4b.domain.model.Item
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemWrapper(
    @SerialName("items")
    val itemList: ArrayList<Item>
)