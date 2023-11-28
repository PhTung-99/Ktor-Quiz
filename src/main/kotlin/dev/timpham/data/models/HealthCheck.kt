package dev.timpham.data.models

import kotlinx.serialization.Serializable

@Serializable
data class HealthCheck(
    val env: String,
    val database: String,
    val redis: String,
)
