package dev.timpham.data.features.quiz.models.request

import dev.timpham.data.features.question.models.QuestionRequest
import dev.timpham.data.features.quiz.models.QuizType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class QuizRequest(
    val name: String,
    val description: String,
    val type: QuizType,
    @Contextual
    val categoryId: UUID,
    val isActive: Boolean,
    val questions: List<QuestionRequest>? = null,
)
