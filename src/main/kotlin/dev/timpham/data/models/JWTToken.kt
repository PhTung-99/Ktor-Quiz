package dev.timpham.data.models

import dev.timpham.core.authentication.JWTUtils
import kotlinx.serialization.Serializable

@Serializable
data class JWTToken(
    val accessToken: String,
    val expiredAt: Long = JWTUtils.validityInMs,
    val refreshToken: String,
    val refreshExpiredAt: Long = JWTUtils.validityRefreshInMs,
)
