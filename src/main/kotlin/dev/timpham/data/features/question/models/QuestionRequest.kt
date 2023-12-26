package dev.timpham.data.features.question.models

import dev.timpham.data.features.answer.models.AnswerRequest
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class QuestionRequest(
    val content: String,
    val highlight: String? = null,
    val isMultipleChoice: Boolean,
    val score: Int,
    @Contextual
    val quizId: UUID? = null,
    val answers: List<AnswerRequest>? = null,
)