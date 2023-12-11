package dev.timpham.data.features.question.models

import dev.timpham.data.features.answers.models.Answer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Question(
    @Contextual
    val id: UUID,
    val content: String,
    val highlight: String? = null,
    val isMultipleChoice: Boolean,
    val score: Int,
    @Contextual
    val quizId: UUID,
    val answers: List<Answer>? = null,
    @Contextual
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
