package dev.timpham.data.features.userAnswerHistory.models

import dev.timpham.data.features.user.models.User
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class UserAnswerHistory(
    @Contextual
    val id: UUID,
    @Contextual
    val quizId: UUID,
    val score: Int,
    @Contextual
    val startTime: Instant,
    @Contextual
    val endTime: Instant,
    val duration: Long,
    val user: User? = null,
)
