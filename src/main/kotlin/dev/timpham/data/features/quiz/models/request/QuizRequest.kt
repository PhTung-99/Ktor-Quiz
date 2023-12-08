package dev.timpham.data.features.quiz.models.request

import dev.timpham.data.features.question.models.QuestionRequest
import dev.timpham.data.features.quiz.models.QuizType
import kotlinx.serialization.Serializable

@Serializable
data class QuizRequest(
    val name: String,
    val description: String,
    val type: QuizType,
    val isActive: Boolean,
    val questions: List<QuestionRequest>? = null,
)
