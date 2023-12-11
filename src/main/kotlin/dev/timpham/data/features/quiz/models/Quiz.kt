package dev.timpham.data.features.quiz.models

import dev.timpham.data.features.question.models.Question
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Quiz(
    @Contextual
    val id: UUID,
    val name: String,
    val description: String,
    val type: QuizType,
    val questions: List<Question>? = null,
    val isActive: Boolean,
    @Contextual
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
