package dev.timpham.data.features.user.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

enum class Role {
    @SerialName("admin")
    ADMIN,
    @SerialName("user")
    USER,
}