package com.onesmartstar.heinhtetaung.q4b.data.repository

import com.onesmartstar.heinhtetaung.q4b.data.remote.Q4BApi
import com.onesmartstar.heinhtetaung.q4b.domain.model.wrapper.ItemWrapper
import com.onesmartstar.heinhtetaung.q4b.domain.model.wrapper.UserWrapper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val q4bApi: Q4BApi
) {

    suspend fun login(
        valueId: String,
        password: String
    ): UserWrapper {
        return q4bApi.loginUser(
            userName = valueId,
            password = password
        )
    }

    suspend fun getAllItemList(
        token: String
    ): ItemWrapper {
        return q4bApi.getItemList(
            token = token
        )
    }

}