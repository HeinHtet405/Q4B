package com.onesmartstar.heinhtetaung.q4b.domain.model.wrapper

import com.onesmartstar.heinhtetaung.q4b.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserWrapper(
    @SerialName("user")
    val userInfo: User
)