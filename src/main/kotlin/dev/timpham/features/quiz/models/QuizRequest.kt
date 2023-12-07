package dev.timpham.features.quiz.models

import dev.timpham.data.features.quiz.models.QuizType
import kotlinx.serialization.Serializable

@Serializable
data class QuizRequest(
    val name: String,
    val description: String,
    val type: QuizType,
    val isActive: Boolean,
)
