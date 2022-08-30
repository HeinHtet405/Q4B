package com.onesmartstar.heinhtetaung.q4b.data.remote

import com.onesmartstar.heinhtetaung.q4b.domain.model.wrapper.ItemWrapper
import com.onesmartstar.heinhtetaung.q4b.domain.model.wrapper.UserWrapper
import retrofit2.http.*

interface Q4BApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("username") userName: String,
        @Field("password") password: String
    ): UserWrapper

    @GET("{token}/projects/plans/52")
    suspend fun getItemList(
        @Path("token") token: String
    ): ItemWrapper
}