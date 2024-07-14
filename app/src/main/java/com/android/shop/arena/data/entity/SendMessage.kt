package com.android.shop.arena.data.entity

data class SendMessage(
    val token: String?,
    val notification: NotificationBody
)

data class NotificationBody(
    val title: String,
    val body: String
)
