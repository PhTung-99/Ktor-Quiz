package dev.timpham.data.features.question.models

import dev.timpham.data.features.answers.models.Answer
import dev.timpham.plugin.serializable.custom.InstantSerializer
import dev.timpham.plugin.serializable.custom.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Question(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val content: String,
    val highlight: String?,
    val score: Int,
    @Serializable(with = UUIDSerializer::class)
    val quizId: UUID,
    val answers: List<Answer>?,
    @Serializable(with = InstantSerializer::class)
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
