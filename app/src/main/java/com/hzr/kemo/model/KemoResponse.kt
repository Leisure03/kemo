package com.hzr.kemo.model

import kotlinx.serialization.Serializable

@Serializable
data class KemoResponse<T> (
    val code: Int,
    val msg: String?= null,
    val data: T?= null,
)

