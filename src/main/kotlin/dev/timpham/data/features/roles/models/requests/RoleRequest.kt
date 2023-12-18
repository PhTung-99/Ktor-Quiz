package dev.timpham.data.features.roles.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class RoleRequest(
    val name: String,
    val description: String
)
