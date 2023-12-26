package dev.timpham.data.features.answer.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Answer(
    @Contextual
    val id: UUID,
    val content: String,
    val isCorrect: Boolean,
    @Contextual
    val questionId: UUID,
    @Contextual
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
