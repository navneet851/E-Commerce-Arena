package com.android.shop.arena.api

import com.android.shop.arena.data.entity.SendMessage
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmApi {

    @POST("/send")
    suspend fun sendMessage(
        @Body body : SendMessage
    )
}