package com.android.shop.arena.data.entity

class Game (
    val id : Int,
    val name : String,
    val coverUri : String,
    val description : String,
    val category : String,
    val soldBy : String,
    val support : List<String>,
    val screenshots : List<String>,
    val totalPrice : String,
    val discountPrice : String,
){
    constructor() : this(-1, "", "", "", "", "", emptyList(), emptyList(), "", "")
}