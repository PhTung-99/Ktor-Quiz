package dev.timpham.data.features.userTokens.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class UserToken(
    @Contextual
    val id: UUID,
    val refreshToken: String,
    @Contextual
    val userId: UUID,
    @Contextual
    val createAtUTC: Instant,
)
