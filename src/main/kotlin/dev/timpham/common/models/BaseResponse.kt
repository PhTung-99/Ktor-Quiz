package dev.timpham.common.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val data: T? = null,
    val messageCode: String? = null,
)