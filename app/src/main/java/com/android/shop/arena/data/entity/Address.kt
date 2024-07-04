package com.android.shop.arena.data.entity

data class Address(
    val name : String,
    val mobile : String,
    val flat : String,
    val city : String,
    val state : String,
    val country : String,
    val pinCode : String,
    val uid : String = ""
) {
    constructor() : this( "", "", "", "", "", "", "")
}