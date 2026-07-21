package com.hzr.kemo.model

import kotlinx.serialization.Serializable

@Serializable
data class BannerEntity(
     val id: Int,
     val imgSrc: String
)