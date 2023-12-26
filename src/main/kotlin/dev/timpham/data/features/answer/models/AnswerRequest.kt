package dev.timpham.data.features.answer.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class AnswerRequest(
    val content: String,
    val isCorrect: Boolean,
    @Contextual
    val questionId: UUID? = null,
)
