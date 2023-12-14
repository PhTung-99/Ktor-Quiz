package dev.timpham.data.features.category.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Category(
    @Contextual
    val id: UUID,
    val name: String,
    val description: String,
    val icon: String,
    val isActive: Boolean,
    @Contextual
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
