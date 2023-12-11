package dev.timpham.data.features.submission.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class UserAnswer(
    @Contextual
    val id: UUID,
    @Contextual
    val quizId: UUID,
    val score: Int,
    @Contextual
    val startTime: Instant,
    @Contextual
    val endTime: Instant,
)
