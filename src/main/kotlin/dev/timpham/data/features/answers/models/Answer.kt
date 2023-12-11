package dev.timpham.data.features.answers.models

import dev.timpham.plugin.serializable.serializer.InstantSerializer
import dev.timpham.plugin.serializable.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Answer(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val content: String,
    val isCorrect: Boolean,
    @Serializable(with = UUIDSerializer::class)
    val questionId: UUID,
    @Serializable(with = InstantSerializer::class)
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
