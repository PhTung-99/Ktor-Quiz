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

@Serializable
data class QuizFullForm(
    val name: String,
    val description: String,
    val type: QuizType,
    val isActive: Boolean,
    val questions: List<QuestionFullForm>,
)

@Serializable
data class QuestionFullForm(
    val content: String,
    val highlight: String? = null,
    val isMultipleChoice: Boolean,
    val score: Int,
    val answers: List<AnswerFullForm>,
)

@Serializable
data class AnswerFullForm(
    val content: String,
    val isCorrect: Boolean,
)
