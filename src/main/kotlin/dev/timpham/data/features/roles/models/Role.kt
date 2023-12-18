package dev.timpham.data.features.roles.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Role(
    @Contextual
    val id: UUID,
    val name: String,
    val description: String
)
