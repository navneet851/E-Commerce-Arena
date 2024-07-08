package com.android.shop.arena.data.entity

data class Transaction(
    val amount : String,
    val dateAndTime : String,
    val uid : String,
    val address : Address,
    val status : Boolean = true,
    val orderedItem: List<OrderedItem>
) {
    constructor() : this("", "", "", Address(), true, emptyList())
}

data class OrderedItem(
    val name : String,
    val quantity : Int
){
    constructor() : this("", 0)
}