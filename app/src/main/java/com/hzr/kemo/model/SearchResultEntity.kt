package com.hzr.kemo.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResultEntity(
    val id: Int,
    val title: String,
    val description: String? = null,
    val imageUrl: String? = null
)
