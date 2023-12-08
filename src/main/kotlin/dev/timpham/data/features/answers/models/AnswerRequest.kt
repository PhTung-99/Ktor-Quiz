package dev.timpham.data.features.answers.models

import dev.timpham.plugin.serializable.custom.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class AnswerRequest(
    val content: String,
    val isCorrect: Boolean,
    @Serializable(with = UUIDSerializer::class)
    val questionId: UUID? = null,
)
