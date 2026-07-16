package com.hzr.kemo.model

import kotlinx.serialization.Serializable

@Serializable
data class DrinkListEntity (
    val id: Int,
    val category: String,
    val bgColor: Int,
    val imgSrc:String,
    val title: String,
    val desc: String,
    val likeStar: Int,
    val price: Int
)