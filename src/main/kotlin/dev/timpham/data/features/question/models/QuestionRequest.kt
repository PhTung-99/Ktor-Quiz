package dev.timpham.data.features.question.models

import dev.timpham.data.features.answers.models.AnswerRequest
import dev.timpham.plugin.serializable.custom.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class QuestionRequest(
    val content: String,
    val highlight: String? = null,
    val isMultipleChoice: Boolean,
    val score: Int,
    @Serializable(with = UUIDSerializer::class)
    val quizId: UUID? = null,
    val answers: List<AnswerRequest>? = null,
)