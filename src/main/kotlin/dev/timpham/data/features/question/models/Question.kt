package dev.timpham.data.features.question.models

import dev.timpham.data.features.answers.models.Answer
import dev.timpham.plugin.serializable.serializer.InstantSerializer
import dev.timpham.plugin.serializable.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Question(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val content: String,
    val highlight: String? = null,
    val isMultipleChoice: Boolean,
    val score: Int,
    @Serializable(with = UUIDSerializer::class)
    val quizId: UUID,
    val answers: List<Answer>? = null,
    @Serializable(with = InstantSerializer::class)
    val createdAtUTC: Instant,
    val isDeleted: Boolean,
)
