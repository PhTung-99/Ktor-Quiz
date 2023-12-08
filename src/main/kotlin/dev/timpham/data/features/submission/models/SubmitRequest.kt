package dev.timpham.data.features.submission.models

import dev.timpham.plugin.serializable.custom.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class SubmitRequest(
    @Serializable(with = UUIDSerializer::class)
    val quizId: UUID,
    val userAnswers: List<UserAnswer>
)

@Serializable
data class UserAnswer(
    @Serializable(with = UUIDSerializer::class)
    val questionId: UUID,
    @Serializable(with = UUIDSerializer::class)
    val answerId: UUID,
)