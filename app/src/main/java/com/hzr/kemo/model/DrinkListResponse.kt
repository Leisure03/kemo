package com.hzr.kemo.model

import kotlinx.serialization.Serializable

@Serializable
data class DrinkListResponse (
    val code: Int,
    val msg: String,
    val data:List<DrinkListEntity>,
    val total: Int,
    val page: Int
)

