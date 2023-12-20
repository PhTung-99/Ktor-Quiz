package dev.timpham.data.features.user.models

import dev.timpham.core.auth.authorization.Role
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class User(
    @Contextual
    val id: UUID,
    val name: String,
    val email: String,
    var password: String? = null,
    val avatar: String? = null,
    val role: Role,
    @Contextual
    val createAtUTC: Instant,
    val isDeleted: Boolean,
)