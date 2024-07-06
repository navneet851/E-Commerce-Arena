package com.android.shop.arena.data.entity

data class Transaction(
    val amount : String,
    val date : String,
    val time : String,
    val uid : String,
    val addressPrint : String,
    val status : Boolean = true
) {
    constructor() : this("", "", "", "", "", true)
}