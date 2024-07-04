package com.android.shop.arena.data.entity

data class Cart(
    val id: Int,
    val quantity: Int,
    val uid : String
) {
    constructor() : this(-1, 0, "")
}