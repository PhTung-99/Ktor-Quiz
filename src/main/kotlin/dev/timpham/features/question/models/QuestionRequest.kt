package dev.timpham.features.question.models

import dev.timpham.plugin.serializable.custom.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class QuestionRequest(
    val content: String,
    val highlight: String,
    @Serializable(with = UUIDSerializer::class)
    val quizId: UUID,
)