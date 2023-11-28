package dev.timpham.features.authentication.models.responses

import dev.timpham.data.features.user.models.User
import dev.timpham.data.models.JWTToken
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: JWTToken,
    val user: User,
)