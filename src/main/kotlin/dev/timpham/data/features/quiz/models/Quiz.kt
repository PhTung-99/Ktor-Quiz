package dev.timpham.data.features.quiz.models

import dev.timpham.data.features.question.models.Question
import dev.timpham.plugin.serializable.custom.InstantSerializer
import dev.timpham.plugin.serializable.custom.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Quiz(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val description: String,
    val type: QuizType,
    val questions: List<Question>? = null,
    val isActive: Boolean,
    @Serializable(with = InstantSerializer::class)
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
