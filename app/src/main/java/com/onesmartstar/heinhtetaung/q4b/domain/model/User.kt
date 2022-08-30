package com.onesmartstar.heinhtetaung.q4b.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("token")
    val token: String
)