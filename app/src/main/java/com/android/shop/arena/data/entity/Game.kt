package com.android.shop.arena.data.entity

class Game (
    val id : Int = -1,
    val name : String = "cartovr",
    val coverUri : String = " ",
    val description : String = " ",
    val category : String = " ",
    val soldBy : String = " ",
    val support : List<String> = emptyList(),
    val screenshots : List<String> = emptyList(),
    val totalPrice : String = " ",
    val discountPrice : String = " ",
){
    constructor() : this(-1, "Cart Is Empty", " ", " ", " ", " ", emptyList(), emptyList(), " ", " ")
}