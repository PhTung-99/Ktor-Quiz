package dev.timpham.data.features.submission.models

import dev.timpham.plugin.serializable.serializer.InstantSerializer
import dev.timpham.plugin.serializable.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class SubmitRequest(
    @Serializable(with = UUIDSerializer::class)
    val quizId: UUID,
    val userAnswers: List<UserAnswer>,
    @Serializable(with = InstantSerializer::class)
    val startTime: Instant,
    @Serializable(with = InstantSerializer::class)
    val endTime: Instant,
)

@Serializable
data class UserAnswer(
    @Serializable(with = UUIDSerializer::class)
    val questionId: UUID,
    @Serializable(with = UUIDSerializer::class)
    val answerId: UUID,
)